# Aurii Mobile App README

## Project Structure

**Follow Angular's recommended structure** with Ionic-specific additions:

```
src/
├── app/
│   ├── core/                 # Singleton services, guards, interceptors
│   ├── shared/               # Reusable components, pipes, directives
│   ├── features/             # Feature modules (lazy-loaded)
│   │   ├── home/
│   │   ├── profile/
│   │   └── settings/
│   ├── layout/               # App shell components (tabs, sidemenu)
│   ├── services/             # Business logic services
│   ├── models/               # TypeScript interfaces/classes
│   └── constants/            # App constants, enums
├── assets/
├── theme/                    # Custom SCSS variables and themes
└── environments/
```

## Module Organization

**Use feature modules with lazy loading** to improve performance:

- Create separate modules for each major feature
- Implement lazy loading for all non-essential routes
- Keep the app module lean with only core imports
- Use shared modules for commonly used components

## Component Architecture

**Follow the container/presentation pattern**:

- **Smart components** (containers): Handle data fetching and state management
- **Dumb components** (presentational): Focus on UI rendering and emit events
- Keep components focused on a single responsibility
- Use OnPush change detection where possible

## Service Layer

**Organize services by purpose**:

- **Data services**: API communication, local storage
- **State services**: Application state management
- **Utility services**: Helper functions, formatters
- Use dependency injection properly with providedIn: 'root' for singletons

## Styling Strategy

**Leverage Ionic's theming system**:

- Define custom CSS properties in `variables.scss`
- Use Ionic color system consistently
- Create component-specific SCSS files
- Avoid global styles except for true app-wide needs
- Use Ionic's responsive utilities

## Navigation Patterns

**Choose the right navigation model**:

- **Tabs**: For peer-level views (most common mobile pattern)
- **Side menu**: For hierarchical navigation with many options
- **Stack navigation**: For linear workflows
- Implement proper back button handling

## State Management

**For complex apps, consider state management**:

- Use services with BehaviorSubject for simple state
- Implement NgRx for complex state requirements
- Keep local component state minimal
- Use Ionic Storage for persistence

## Performance Optimization

**Implement these performance practices**:

- Use lazy loading for routes
- Implement virtual scrolling for large lists
- Optimize images and use appropriate formats
- Use OnPush change detection strategically
- Implement proper memory cleanup in ngOnDestroy

## Code Organization Standards

**Maintain consistency**:

- Use barrel exports (index.ts files) for clean imports
- Follow Angular naming conventions strictly
- Implement proper error handling throughout
- Use TypeScript strictly with proper typing
- Create reusable validators for forms

## Testing Structure

**Organize tests effectively**:

- Unit tests alongside components/services
- Integration tests in separate e2e folder
- Use Ionic's testing utilities
- Mock platform-specific features properly

---

This structure scales well from small to enterprise applications while maintaining Ionic's mobile-first principles and Angular's architectural patterns.