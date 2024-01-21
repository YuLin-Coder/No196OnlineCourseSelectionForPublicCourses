import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 权限路由
	{
		path: '/auth/table',
		name: 'auth_table',
		component: () => import('../views/nav/table.vue'),
		meta: {
			index: 0,
			title: '权限列表'
		}
	},
	{
		path: '/auth/view',
		name: 'auth_view',
		component: () => import('../views/nav/view.vue'),
		meta: {
			index: 0,
			title: '权限详情'
		}
	},

	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
	// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '新闻列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '新闻详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '新闻分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '新闻分类详情'
		}
	},

	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },
	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告详情'
		}
	},
	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},
	// 教师路由
	{
		path: '/teacher/table',
		name: 'teacher_table',
		component: () => import('../views/teacher/table.vue'),
		meta: {
			index: 0,
			title: '教师列表'
		}
	},
	{
		path: '/teacher/view',
		name: 'teacher_view',
		component: () => import('../views/teacher/view.vue'),
		meta: {
			index: 0,
			title: '教师信息'
		}
	},
	// 学生路由
	{
		path: '/student/table',
		name: 'student_table',
		component: () => import('../views/student/table.vue'),
		meta: {
			index: 0,
			title: '学生列表'
		}
	},
	{
		path: '/student/view',
		name: 'student_view',
		component: () => import('../views/student/view.vue'),
		meta: {
			index: 0,
			title: '学生信息'
		}
	},
	// 专业分类路由
	{
		path: '/professional_classification/table',
		name: 'professional_classification_table',
		component: () => import('../views/professional_classification/table.vue'),
		meta: {
			index: 0,
			title: '专业分类列表'
		}
	},
	{
		path: '/professional_classification/view',
		name: 'professional_classification_view',
		component: () => import('../views/professional_classification/view.vue')
	},
	// 课程路由
	{
		path: '/curriculum/table',
		name: 'curriculum_table',
		component: () => import('../views/curriculum/table.vue'),
		meta: {
			index: 0,
			title: '课程列表'
		}
	},
	{
		path: '/curriculum/view',
		name: 'curriculum_view',
		component: () => import('../views/curriculum/view.vue')
	},
	// 选课管理路由
	{
		path: '/course_selection_management/table',
		name: 'course_selection_management_table',
		component: () => import('../views/course_selection_management/table.vue'),
		meta: {
			index: 0,
			title: '学生选课管理'
		}
	},
	{
		path: '/course_selection_management/view',
		name: 'course_selection_management_view',
		component: () => import('../views/course_selection_management/view.vue')
	},
	// 教学任务路由
	{
		path: '/teaching_task/table',
		name: 'teaching_task_table',
		component: () => import('../views/teaching_task/table.vue'),
		meta: {
			index: 0,
			title: '教学任务管理'
		}
	},
	{
		path: '/teaching_task/view',
		name: 'teaching_task_view',
		component: () => import('../views/teaching_task/view.vue')
	},
	// 学院管理路由
	{
		path: '/college_management/table',
		name: 'college_management_table',
		component: () => import('../views/college_management/table.vue'),
		meta: {
			index: 0,
			title: '学院列表'
		}
	},
	{
		path: '/college_management/view',
		name: 'college_management_view',
		component: () => import('../views/college_management/view.vue')
	},
	// 班级管理路由
	{
		path: '/class_management/table',
		name: 'class_management_table',
		component: () => import('../views/class_management/table.vue'),
		meta: {
			index: 0,
			title: '班级列表'
		}
	},
	{
		path: '/class_management/view',
		name: 'class_management_view',
		component: () => import('../views/class_management/view.vue')
	},
	// 成绩管理路由
	{
		path: '/achievement_management/table',
		name: 'achievement_management_table',
		component: () => import('../views/achievement_management/table.vue'),
		meta: {
			index: 0,
			title: '成绩管理'
		}
	},
	{
		path: '/achievement_management/view',
		name: 'achievement_management_view',
		component: () => import('../views/achievement_management/view.vue')
	},
	// 通知学生路由
	{
		path: '/inform_students/table',
		name: 'inform_students_table',
		component: () => import('../views/inform_students/table.vue'),
		meta: {
			index: 0,
			title: '学生通知'
		}
	},
	{
		path: '/inform_students/view',
		name: 'inform_students_view',
		component: () => import('../views/inform_students/view.vue')
	},
	// 通知教师路由
	{
		path: '/inform_the_teacher/table',
		name: 'inform_the_teacher_table',
		component: () => import('../views/inform_the_teacher/table.vue'),
		meta: {
			index: 0,
			title: '教师通知'
		}
	},
	{
		path: '/inform_the_teacher/view',
		name: 'inform_the_teacher_view',
		component: () => import('../views/inform_the_teacher/view.vue')
	},
	// 数据备份路由
	{
		path: '/data_backup/table',
		name: 'data_backup_table',
		component: () => import('../views/data_backup/table.vue'),
		meta: {
			index: 0,
			title: '数据备份'
		}
	},
	{
		path: '/data_backup/view',
		name: 'data_backup_view',
		component: () => import('../views/data_backup/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '管理员列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "公选课在线选课系统-admin";
	document.title = title;
})

export default router
