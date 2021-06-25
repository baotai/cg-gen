import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/system',
    name: 'System',
    meta: { title: '系统管理', icon: 'dashboard' },
    component: Layout,
    children: [
      {
        path: '/user',
        name: 'User',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '用户管理', icon: 'dashboard' }
      },
      {
        path: '/role',
        name: 'Role',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '角色管理', icon: 'dashboard' }
      },
      {
        path: '/permission',
        name: 'Permission',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '权限管理', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/gen',
    name: 'Gen',
    meta: { title: '代码生成工具', icon: 'dashboard' },
    component: Layout,
    children: [
      {
        path: '/generate/config',
        name: 'Config',
        component: () => import('@/views/generate/index'),
        meta: { title: '生成代码', icon: 'form' }
      },
      {
        path: '/result/:config',
        name: 'Result',
        component: () => import('@/views/generate/result'),
        meta: { title: '生成结果' },
        hidden: true
      },
      {
        path: '/group',
        name: 'Group',
        component: () => import('@/views/group/index'),
        meta: { title: '模板组管理', icon: 'example' }
      },
      {
        path: '/template/list',
        name: 'List',
        component: () => import('@/views/template/index'),
        meta: { title: '模板管理', icon: 'table' }
      },
      {
        path: '/template/edit/:id(\\d+)',
        name: 'Edit',
        component: () => import('@/views/template/edit'),
        meta: { 'title': '编辑模板', icon: 'table' },
        hidden: true
      }
    ]
  },
  {
    path: '/blog',
    name: 'Blog',
    meta: { title: '博文管理', icon: 'dashboard' },
    component: Layout,
    children: [
      {
        path: '/blog/list',
        name: 'BlogList',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '博文管理', icon: 'dashboard' }
      },
      {
        path: '/role',
        name: 'Role',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '角色管理', icon: 'dashboard' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
