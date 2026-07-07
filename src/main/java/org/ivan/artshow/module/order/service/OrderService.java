package org.ivan.artshow.module.order.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.repository.AddRepository;
import org.ivan.artshow.module.order.pojo.Order;
import org.ivan.artshow.module.order.pojo.OrderShippingAddress;
import org.ivan.artshow.module.order.pojo.dto.OrderDTO;
import org.ivan.artshow.module.order.repository.OrderRepository;
import org.ivan.artshow.module.order.repository.OrderShippingAddressRepository;
import org.ivan.artshow.module.course.pojo.Course;
import org.ivan.artshow.module.course.repository.CourseRepository;
import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.ivan.artshow.module.orderitem.repository.OrderitemRepository;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.repository.ProductRepository;
import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.repository.SciRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * OrderService - 业务服务实现类
 *
 * <p>OrderService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final AddRepository addRepository;
    private final OrderShippingAddressRepository shippingAddressRepository;
    private final SciRepository sciRepository;
    private final ProductRepository productRepository;
    private final OrderitemRepository orderitemRepository;
    private final CourseRepository courseRepository;

    public OrderService(OrderRepository orderRepository,
                        AddRepository addRepository,
                        OrderShippingAddressRepository shippingAddressRepository,
                        SciRepository sciRepository,
                        ProductRepository productRepository,
                        OrderitemRepository orderitemRepository,
                        CourseRepository courseRepository) {
        this.orderRepository = orderRepository;
        this.addRepository = addRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.sciRepository = sciRepository;
        this.productRepository = productRepository;
        this.orderitemRepository = orderitemRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Order addOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer currentUserId = UserContext.getUserId();

        Order nOrder = new Order();
        BeanUtils.copyProperties(orderDTO, nOrder);
        nOrder.setUserId(currentUserId); // 🔒 强制绑定当前用户
        nOrder.setCreatedAt(new Date());
        nOrder = orderRepository.save(nOrder);

        // 处理地址快照
        if (orderDTO.getAddressId() != null) {
            Address userAddr = addRepository.findById(orderDTO.getAddressId())
                    .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

            // 🔒 额外检查：下单用的地址必须是自己的
            if (!userAddr.getUserId().equals(currentUserId)) {
                throw new BizException(ResultCodes.UNAUTH);
            }

            OrderShippingAddress snapshot = new OrderShippingAddress();
            snapshot.setOrderId(nOrder.getOrderId());
            snapshot.setRecipientName(userAddr.getRecipientName());
            snapshot.setPhone(userAddr.getPhone());
            snapshot.setRegion(userAddr.getRegion());
            snapshot.setDetail(userAddr.getDetailAddress());
            shippingAddressRepository.save(snapshot);
        }
        return nOrder;
    }

    @Override
    public void deleteOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        if (orderDTO.getOrderId() == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        // 订单通常不允许用户直接Update，通常是修改状态（取消/支付）
        // 这里仅做示例修复
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        BeanUtils.copyProperties(orderDTO, order);
        return orderRepository.save(order);
    }

    @Override
    public Order queryOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查：只有订单主人才看详情
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return order;
    }

    // 🔒 新增：查我的订单（实现之前的讨论）
    @Override
    public List<Order> findMyOrders() {
        Integer currentUserId = UserContext.getUserId();
        return orderRepository.findByUserId(currentUserId);
    }

    @Override
    public List<Order> queryAllOrderBatch(List<Integer> userIdlist) {
        if (userIdlist == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        // 管理员接口，暂时保持原样
        return orderRepository.findAllById(userIdlist);
    }

    @Override
    public List<Order> findAllOrders() {
        // 管理员接口，暂时保持原样
        return orderRepository.findAll();
    }

    /**
     * 从购物车创建订单（完整流程）
     * 1. 从购物车项获取商品信息
     * 2. 检查每个商品的库存是否充足
     * 3. 生成唯一订单号
     * 4. 创建订单主表记录
     * 5. 创建所有订单项，填充价格快照和商品快照
     * 6. 扣减商品库存
     * 7. 清空已结算的购物车项
     */
    @Override
    @Transactional
    public Order createOrderFromCart(List<Integer> cartItemIds, Integer addressId) {
        if (cartItemIds == null || cartItemIds.isEmpty()) {
            throw new BizException(ResultCodes.INVALID_PARAM, "购物车为空");
        }
        if (addressId == null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "请选择收货地址");
        }

        Integer currentUserId = UserContext.getUserId();

        // 1. 获取购物车项
        List<Sci> cartItems = sciRepository.findByCartItemIdIn(cartItemIds);
        if (cartItems.isEmpty()) {
            throw new BizException(ResultCodes.NOTFOUND, "购物车项不存在");
        }

        // 验证所有购物车项都属于当前用户
        for (Sci cartItem : cartItems) {
            if (!cartItem.getUserId().equals(currentUserId)) {
                throw new BizException(ResultCodes.UNAUTH, "购物车项不属于当前用户");
            }
        }

        // 2. 获取商品信息并检查库存
        List<Product> products = new ArrayList<>();
        double totalPrice = 0.0;

        for (Sci cartItem : cartItems) {
            // 验证购物车项数量
            if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
                throw new BizException(ResultCodes.INVALID_PARAM, "购物车项数量无效");
            }

            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商品不存在: " + cartItem.getProductId()));

            // 验证商品价格
            if (product.getPrice() == null) {
                throw new BizException(ResultCodes.INVALID_PARAM, "商品 [" + product.getName() + "] 价格未设置");
            }

            // 检查库存
            if (product.getStock() == null || product.getStock() < cartItem.getQuantity()) {
                throw new BizException(ResultCodes.INVALID_PARAM,
                    "商品 [" + product.getName() + "] 库存不足，当前库存: " + product.getStock());
            }

            products.add(product);
            totalPrice += product.getPrice() * cartItem.getQuantity();
        }

        // 3. 验证地址属于当前用户
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "地址不存在"));
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "地址不属于当前用户");
        }

        // 4. 生成订单号 (格式: ORD + yyyyMMddHHmmss + 4位随机数)
        String orderNumber = generateOrderNumber();

        // 5. 创建订单主表
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setUserId(currentUserId);
        order.setTotalPrice(totalPrice);
        order.setStatus("pending"); // 待支付
        order.setCreatedAt(new Date());
        order = orderRepository.save(order);

        // 6. 创建订单地址快照
        OrderShippingAddress snapshot = new OrderShippingAddress();
        snapshot.setOrderId(order.getOrderId());
        snapshot.setRecipientName(address.getRecipientName());
        snapshot.setPhone(address.getPhone());
        snapshot.setRegion(address.getRegion());
        snapshot.setDetail(address.getDetailAddress());
        shippingAddressRepository.save(snapshot);

        // 7. 创建订单项并扣减库存
        for (int i = 0; i < cartItems.size(); i++) {
            Sci cartItem = cartItems.get(i);
            Product product = products.get(i);

            // 扣减库存（原子操作，防止超卖）
            int affectedRows = productRepository.deductStock(product.getId(), cartItem.getQuantity());
            if (affectedRows == 0) {
                throw new BizException(ResultCodes.INVALID_PARAM,
                    "商品 [" + product.getName() + "] 库存不足");
            }

            // 创建订单项（保存价格快照和商品信息快照）
            Orderitem orderItem = new Orderitem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice()); // 价格快照
            orderItem.setProductNameSnapshot(product.getName()); // 商品名称快照
            orderItem.setProductImageSnapshot(product.getImageUrl()); // 商品图片快照
            orderitemRepository.save(orderItem);
        }

        // 8. 清空已结算的购物车项
        sciRepository.deleteAllById(cartItemIds);

        return order;
    }

    /**
     * 生成唯一订单号
     * 格式: ORD + yyyyMMddHHmmss + 4位随机数
     */
    private String generateOrderNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int randomNum = new Random().nextInt(9000) + 1000; // 1000-9999
        return "ORD" + timestamp + randomNum;
    }

    /**
     * 直接购买课程（创建课程订单）
     * 课程订单不需要地址，创建后立即可以支付
     */
    @Override
    @Transactional
    public Order purchaseCourse(Integer courseId) {
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Integer currentUserId = UserContext.getUserId();

        // 1. 检查课程是否存在
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "课程不存在"));

        // 2. 验证课程类型不为空
        if (course.getType() == null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "课程类型未设置");
        }

        // 3. 检查是否为付费课程
        if (!"paid".equalsIgnoreCase(course.getType())) {
            throw new BizException(ResultCodes.INVALID_PARAM, "该课程为免费课程，无需购买");
        }

        // 4. 验证课程价格不为空
        if (course.getPrice() == null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "付费课程价格未设置");
        }

        // 5. 检查是否已购买
        boolean hasPurchased = orderitemRepository.existsPaidCourseByUserIdAndCourseId(currentUserId, courseId);
        if (hasPurchased) {
            throw new BizException(ResultCodes.INVALID_PARAM, "您已经购买过该课程");
        }

        // 6. 生成订单号
        String orderNumber = generateOrderNumber();

        // 7. 创建订单主表
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setUserId(currentUserId);
        order.setTotalPrice(course.getPrice());
        order.setStatus("pending"); // 待支付
        order.setCreatedAt(new Date());
        order = orderRepository.save(order);

        // 6. 创建订单项（课程订单）
        Orderitem orderItem = new Orderitem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setCourseId(courseId); // 设置课程ID
        orderItem.setProductId(null); // 课程订单不关联商品
        orderItem.setQuantity(1); // 课程数量固定为1
        orderItem.setPriceAtPurchase(course.getPrice()); // 价格快照
        orderItem.setProductNameSnapshot(course.getTitle()); // 课程标题作为名称快照
        orderItem.setProductImageSnapshot(course.getCoverImageUrl()); // 课程封面作为图片快照
        orderitemRepository.save(orderItem);

        return order;
    }

    /**
     * 取消订单（恢复库存）
     * 仅当订单状态为 pending 或 paid（未发货）时允许取消
     */
    @Override
    @Transactional
    public void cancelOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "订单不存在"));

        // 权限检查
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权操作此订单");
        }

        // 状态检查
        if (!"pending".equals(order.getStatus()) && !"paid".equals(order.getStatus())) {
            throw new BizException(ResultCodes.INVALID_PARAM,
                "只有待支付或已支付未发货的订单可以取消");
        }

        // 恢复库存（仅对商品订单，课程订单无需恢复库存）
        List<Orderitem> orderItems = orderitemRepository.findByOrderId(orderId);
        for (Orderitem item : orderItems) {
            if (item.getProductId() != null) {
                // 商品订单项：恢复库存
                productRepository.restoreStock(item.getProductId(), item.getQuantity());
            }
            // 课程订单项：无需恢复库存
        }

        // 更新订单状态
        order.setStatus("cancelled");
        orderRepository.save(order);
    }

    /**
     * 支付订单
     */
    @Override
    @Transactional
    public void payOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "订单不存在"));

        // 权限检查
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权操作此订单");
        }

        // 状态检查
        if (!"pending".equals(order.getStatus())) {
            throw new BizException(ResultCodes.INVALID_PARAM, "订单状态不正确");
        }

        order.setStatus("paid");
        orderRepository.save(order);
    }

    /**
     * 发货
     */
    @Override
    @Transactional
    public void shipOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "订单不存在"));

        // 状态检查
        if (!"paid".equals(order.getStatus())) {
            throw new BizException(ResultCodes.INVALID_PARAM, "只有已支付的订单可以发货");
        }

        order.setStatus("shipped");
        orderRepository.save(order);
    }

    /**
     * 完成订单
     */
    @Override
    @Transactional
    public void completeOrder(Integer orderId) {
        if (orderId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "订单不存在"));

        // 权限检查
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权操作此订单");
        }

        // 状态检查
        if (!"shipped".equals(order.getStatus())) {
            throw new BizException(ResultCodes.INVALID_PARAM, "只有已发货的订单可以完成");
        }

        order.setStatus("completed");
        orderRepository.save(order);
    }
}
