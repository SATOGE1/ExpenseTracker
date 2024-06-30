import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

function authGuard(to, from, next)
{
  if(authenticated()) {
    next();
  } else{
    next('/login');
  }
}

function guestGuard(to, from, next)
{
  if(!authenticated()) {
    next();
  } else{
    next('/');
  }
}

function expenseSelectedGuard(to, from, next)
{
  if(null != localStorage.getItem('description')) {
    next();
  } else {
    next('/expenses/index');
  }
}

function authenticated() {
  var isAuthenticated = false;

  if(null != localStorage.getItem('token')) {
    isAuthenticated = true;
  }
  else {
    isAuthenticated = false;
  }

  return isAuthenticated;
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/auth',
      name: 'auth',
      beforeEnter : guestGuard,
      component: () => import('../views/AuthView.vue'),
    },
    {
      path: '/expenses/create',
      name: 'create expense',
      beforeEnter : authGuard,
      component: () => import('../views/CreateExpenseView.vue'),
    },
    {
      path: '/expenses/index',
      name: 'view expenses',
      beforeEnter : authGuard,
      component: () => import('../views/IndexExpenseView.vue'),
    },
    {
      path: '/expenses/analyse',
      name: 'view analysis',
      beforeEnter : authGuard,
      component: () => import('../views/AnalyseDataView.vue'),
    },
    {
      path: '/expenses/edit/:id',
      name: 'edit expense',
      beforeEnter : [authGuard, expenseSelectedGuard],
      component: () => import('../views/EditExpenseView.vue'),
    },
  ],
});

export default router;
