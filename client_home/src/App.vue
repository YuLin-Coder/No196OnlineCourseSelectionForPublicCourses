<template>
    <div id="app">
        <page_header/>
        <div class="space"/>
        <transition name="fade-transform" mode="out-in">
            <router-view :key="$route.fullPath"/>
        </transition>
        <page_footer/>
    </div>
</template>

<style lang='less'>
    @import "/css/base.css";
    @import "/css/theme.css";

    .page-item.active .page-link {
        background-color: var(--color_primary) !important;
        border-color: var(--color_primary) !important;
    }

    .btn-outline-primary {
        color: var(--color_primary) !important;
        border-color: var(--color_primary) !important;
    }

    //放大镜
    .mouse-cover-canvas {
        position: absolute;
        left: calc(50% - 6rem) !important;
        top: 8rem !important;
    }

    body {
        background-color: #F4F7F9 !important;
        font-family: STXihei !important;
    }

    [class="col-"] {
        padding: 0;
    }

    .space {
        height: 5rem;
    }

    .page_header {
        position: fixed;
        top: 0;
        z-index: 1000;
        width: 100%;
    }

    a {
        color: inherit !important;
        text-decoration: none;
        -webkit-transition: all 0.3s ease 0s;
        -o-transition: all 0.3s ease 0s;
        transition: all 0.3s ease 0s;
    }

    a:hover {
        text-decoration: none !important;
        /*color: var(--color_primary) !important;*/
        color: #909399 !important;
    }

    .toast {
        opacity: 1 !important;
        min-width: 200px;
    }

    .b-toaster-top-right {
        position: absolute;
        top: 10rem;
        left: 50%;
        transform: translate(-50%);
    }

    figure,
    p,
    h1,
    h2,
    h3,
    h4,
    h5,
    h6,
    ul,
    li {
        margin: 0;
        padding: 0;
    }

    .fade-transform-leave-active,
    .fade-transform-enter-active {
        transition: all 0.5s;
    }

    .fade-transform-enter {
        opacity: 0;
        transform: translateX(-30px);
    }

    .fade-transform-leave-to {
        opacity: 0;
        transform: translateX(30px);
    }
	
	@import "/css/diy.css";
	/* 5EB95E */
	.page_header .header_warp .my_header .nav_list ul li a {
		border-width: 0!important;
	}
	.page_header .header_warp .search-box .search-txt {
		    background-color: white!important;
	}
	.page_header .header_warp .user {
		color: #666666!important;
	}
	.page_header .header_warp .logo {
		color: #5EB95E!important;
	}
	.page_header .header_warp .my_header .nav_list ul li :hover, .page_header .header_warp .user .own:hover {
		color: #5EB95E!important;
	}
	#root_demo .card_notice {
		background-color: #EAF3EE!important;
	}
	#root_demo .card_notice .title {
		box-sizing: unset!important;
	}
	#root_demo .article_recommend, #root_demo .diy_recommend {
		background-color: #EAF3EE!important;
	}
	.list, .info {
		background-color: #EAF3EE!important;
	}
	#root_demo nav a .article {
		border: 1px solid #f1f1f1!important;
		border-radius: 4px!important;
	}
	#root_demo nav .article .doc .title {
		color: #666666!important;
	}
	#root_demo nav .article .doc .interact .praise, #root_demo nav .article .doc .interact .see {
		color: #999999!important;
	}
	.my_header .user div:nth-child(2) {
		background-color: white!important;
		border-width: 0!important;
	}
	#app .dropdown .my_item a {
		background-color: white!important;
		color: #333333!important;
		border-width: 0!important;
		border-radius: 0!important;
	}
	#app .dropdown .my_item:hover a, .dropdown .my_item:hover,  .dropdown .my_item:hover div{
		background-color: #5EB95E!important;
		color: white!important;
	}
	.flex_cc ul, .flex_cc ul li {
		background-color: transparent!important;
	}
	.page-item.active .page-link {
		background-color: #5EB95E22!important;
		color: #666666!important;
		border-color: #5EB95E22!important;
	}	
	#article_list .article_list_title {
		background-color: white!important;
		color: #333333!important;
	}
	.bar_filter, .bar_sort {
		border-color: #5EB95E!important;
	}
	#article_list .list_article .article_warp .doc .title {
		color: #666666!important;
	}
	#article_list .card_article_hot {
		background-color: white!important;
	}
	.pagination {
		justify-content: center!important;
	}
	.diy_home .diy_view_list a {
		border: 2px solid transparent!important;
	}
	.diy_home .diy_view_list a:hover {
		border-color: #5EB95E77!important;
	}
	.diy_list .diy_view_list .diy_card .diy_title, #root_demo .row .diy_view_list .diy_title {
		color: #999999!important;
	}
	.dropdown .dropdown-menu {
		background-color: white!important;
	}
	.notice_content {
		background-color: white!important;
		min-height: 200px;
	}
	.div_article {
		background-color: white!important;
	}
	.details_title {
		background-color: #5EB95E!important;
	}
	.row_top_wrap .row_2 .view .diy_title {
		color: #999999!important;
	}
	.praise_wrap .diy_text {
		color: black;
	}
	.details_btn_wrap button {
		background-color: #5EB95E!important;
	}
	.diy_details .fn .btn_publish .btn {
		background-color: #5EB95E!important;
	}
	.diy_details .view .diy_title {
		color: #333333!important;
	}
	#account_login .card .form_login {
		border-radius: 5px!important;
	}
	#account_login .form_login input, #account_login .forget_container input, #account_register .form_register input {
		border-width: 0 0 1px 0;
		    border-radius: 0;
	}
</style>
