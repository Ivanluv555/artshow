# Artshow Frontend

Vue 3 + Vite frontend for Artshow - Art Exhibition and Course Management System

## Features

- 🎨 **Art Categories**: Browse and explore different art categories and subcategories
- 📚 **Online Courses**: View and enroll in art courses
- 🛍️ **Products**: Shop for art products and supplies
- 💬 **Community**: Create posts, like, comment, and interact with other users
- 👤 **User Profile**: Manage profile, view enrolled courses and orders
- 🔐 **Authentication**: JWT-based login and registration

## Tech Stack

- **Vue 3** - Progressive JavaScript framework
- **Vue Router** - Official router for Vue.js
- **Element Plus** - Vue 3 UI component library
- **Axios** - HTTP client
- **Vite** - Next generation frontend tooling

## Prerequisites

- Node.js 16+ and npm

## Installation

```bash
# Install dependencies
npm install
```

## Development

```bash
# Start dev server at http://localhost:3000
npm run dev
```

The dev server includes proxy configuration for API requests:
- Frontend: `http://localhost:3000`
- Backend API: `http://localhost:8888` (proxied through `/api`)

## Build

```bash
# Build for production
npm run build

# Preview production build
npm run preview
```

## Project Structure

```
frontend/
├── src/
│   ├── api/              # API modules
│   │   ├── index.js      # API exports
│   │   └── request.js    # Axios instance and interceptors
│   ├── assets/           # Static assets
│   ├── components/       # Reusable components
│   ├── router/           # Vue Router configuration
│   │   └── index.js
│   ├── utils/            # Utility functions
│   ├── views/            # Page components
│   │   ├── Home.vue
│   │   ├── Login.vue
│   │   ├── Categories.vue
│   │   ├── Courses.vue
│   │   ├── Products.vue
│   │   ├── Posts.vue
│   │   └── Profile.vue
│   ├── App.vue           # Root component
│   └── main.js           # Application entry
├── index.html            # HTML template
├── package.json
└── vite.config.js        # Vite configuration
```

## API Integration

All API calls are handled through axios instances with:
- Automatic Bearer token injection
- Response interceptors for error handling
- Base URL configuration through Vite proxy

### Authentication

After successful login, the JWT token is stored in localStorage and automatically included in all API requests.

## Available Routes

- `/` - Home page
- `/login` - Login/Register page
- `/categories` - Art categories
- `/courses` - Online courses
- `/products` - Art products shop
- `/posts` - Community posts
- `/profile` - User profile (requires authentication)

## Configuration

### Proxy Configuration

Edit `vite.config.js` to change the backend API URL:

```js
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8888',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/api/, '')
    }
  }
}
```

## Environment Variables

Create a `.env` file for custom configuration:

```
VITE_API_BASE_URL=http://localhost:8888
```

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## License

MIT

## Author

Ivan Horn
