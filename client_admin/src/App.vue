<template>
	<div class="app" id="app">
		<div class="page" v-if="$store.state.user.user_id === 0 || url.indexOf($route.path) !== -1 ">
			<router-view />
		</div>
		<div class="page" v-else>
			<!-- 侧边栏 -->
			<page_side :isCollapse="isCollapse"></page_side>
			<!-- 主体 -->
			<main>
				<!-- 页头 -->
				<page_header v-model="isCollapse" />
				<article>
					<haipa></haipa>
					<router-view />
				</article>
			</main>
		</div>
	</div>
</template>

<style>
	@import "/css/base.css";
	@import "/css/theme.css";

	.mr-1 {
		margin-right: 1rem;
	}

	.quill-editor {
		background: #FFFFFF;
	}

	.ql-editor {
		min-height: 25rem;
	}

	.editor {
		min-height: 500px;
	}

	.el-dialog {
		width: calc(100% - 1rem);
		max-width: 40rem;
	}

	.field_action {
		text-align: center;
	}

	.field_action .el-transfer {
		display: inline-block;
		text-align: left;
	}

	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
		-webkit-appearance: none;
	}

	input[type=number] {

		&::-webkit-outer-spin-button,
		&::-webkit-inner-spin-button {
			-webkit-appearance: none;
		}

		-moz-appearance: textfield;
	}

	@media (min-width: 576px) {
		::-webkit-scrollbar-track-piece {
			background-color: #f8f8f8;
		}

		/* 滚动条的宽度 */
		::-webkit-scrollbar {
			width: .5rem;
			height: .5rem;
		}

		/* 滚动条的设置 */
		::-webkit-scrollbar-thumb {
			background-color: rgba(0, 0, 0, 0.5);
			background-clip: padding-box;
			min-height: 1rem;
			border-radius: 0.5rem;
		}
	}

	.avatar-uploader .el-upload-dragger {
		width: 178px;
	}

	table a {
		color: inherit;
		text-decoration: none;
	}

	.el-input {
		max-width: 22.625rem;
	}

	body {
		padding: 0;
		margin: 0;
		background-color: #efeff4;
	}

	main {
		margin: 0 auto;
		overflow-x: hidden;
		width: 100%;
	}


	header i {
		font-size: 1.75rem;
	}

	article {
		box-sizing: border-box;
		height: calc(100vh - 3.375rem);
		padding: .5rem;
		overflow-y: auto;
	}

	button i::before {
		font-weight: bold;
	}

	.el-breadcrumb {
		padding: .5rem;
	}


	.page {
		display: flex;
		align-items: stretch;
	}

	.btn_full {
		width: 100%;
	}

	.el-table .cell {
		white-space: nowrap;
	}

	.ql-editor {
		height: 300px;
	}

	.diy_panel .el-radio {
		padding: 9px 15px 9px 15px;
	}

	.diy_panel .el-checkbox {
		padding: 9px 15px 9px 15px;
	}

	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.float-right {
		float: right;
	}

	.mr-1 {
		margin-right: 1rem;
	}

	.el-table .table_class {
		background: rgba(150, 150, 150, 0.1);
		text-align: center;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}

	.warp {
		width: 100%;
	}

	.el-col {
		overflow: hidden;
	}

	.ql-picker {
		height: initial !important;
	}
	
	@import "/css/diy.css";
	
</style>

<script>
	export default {
		data() {
			return {
				isCollapse: false,
				url: ['/login', '/forgot', '/register']
			}
		},
		methods: {
			init() {
				var token = $.db.get('token');
				if (token) {
					this.$store.commit('user_set', {
						token
					});
				}
			}
		},
		created() {
			this.init();
		}
	}
</script>
<style>
	#app .sign_in {
		background-image: url(./assets/login_bg.jpg)!important;
		background-size: cover!important;
	}
	.sign_in .form {
		background-color: white!important;
		border-radius: 0!important;
		border-width: 0!important;
		padding: 3rem!important;
		box-shadow: unset!important;
	}
	.sign_in .form h1 {
		position: relative!important;
		top: 0!important;
		border-radius: 0!important;
		border-width: 0!important;
		font-size: 24px!important;
		font-size: 28px!important;
		height: 5rem;
		line-height: 5rem;
	}
	.sign_in .form .el-form-item {
		margin: 20px 0!important;
	}
	.sign_in .form label {
		display: none!important;
	}
	.sign_in .form .el-form-item .el-form-item__content {
		margin-left: 0!important;
	}
	.sign_in .form input {
		border-width: 0 0 1px 0!important;
		border-radius: 0!important;
	}
	.form .el-input{
		width: 100%!important;
	}
	.sign_in .btns .el-button {
		width: 100%!important;
		margin-top: 20px;
	}
	.sign_in .btns:last-child {
		background-color: unset!important;
		border-width: 0!important;
		color: #333333!important;
	}
	
	#app .page .side {
		background-color: #20222A!important;
	}
	#app .page .side .el-menu-item {
		color: white!important;
		background-color: unset!important;
	}
	#app .page .side .el-submenu__title {
		border-radius: 0!important;
		border-width: 0!important;
		background-color: unset!important;
		color: #b9b9b9!important;
		border-left: 4px solid transparent!important;
	}
	#app .page .side .el-submenu__title:hover {
		color: white!important;
		border-left: 4px solid #1686ef!important;
	}
	#app .page .side .el-submenu__title span {
		color: inherit!important;
	}
	#app .page .side .el-submenu.is-opened .el-submenu__title{
		background-color: #1686ef!important;
		color: white!important;
	}
	#app .page .side .el-submenu li {
		margin: 0!important;
		box-shadow: unset!important;
		width: auto!important;
		padding: 0!important;
		text-align: left!important;
		text-indent: 30px!important;
		border-left: 4px solid transparent!important;
		background-color: rgba(32,34,42, .7)!important;
		font-size: 1rem!important;
	}
	#app .page .side .el-menu-item.is-active {
		color: white!important;
		border-left: 4px solid #1686ef!important;
		background-color: #20222A!important;
		font-size: 1rem!important;
	}
	.page article {
		background-color: #f1f1f1!important;
		background-image: unset!important;
		padding: 15px!important;
		border-width: 0!important;
	}
	#app header {
		background-color: white!important;
		box-shadow: 0 0 15px rgba(0, 0, 0, 1)!important;
	}
	#app header .action_center {
		color: #333333!important;
		text-align: left!important;
		text-indent: 40px!important;
	}
	#app header .action_right .el-dropdown span:nth-child(2) {
		color: #333!important;
	}
	.form .el-input .el-input__inner, .el-form .el-input .el-input__inner{
		border-radius: 0!important;
		height: 32px!important;
	}
	.el-form-item .el-button {
		padding: 10px 15px!important;
		background-color: #5EB95E!important;
		color: white!important;
		border-width: 0!important;
		max-width: 200px;
	}
	.el-table td, .el-table th {
		border-width: 0 0 1px 0!important;
		border-color: #d9d9d9!important;
	}
	#app .el-button--primary.is-plain {
		padding: 10px 15px!important;
		background-color: white!important;
		color: #333!important;
		border-width: 1!important;
		border-color: #d9d9d9!important;
		transition: all. 3s ease;
	}
	#app .el-button--primary.is-plain:hover {
		background-color: #5EB95E!important;
		color: white!important;
	}
	.el-table td, .el-table th {
		padding: 8px 0!important;
	}
	.el-pagination {
		display: flex;
		justify-content: flex-end;
	}
	.container-fluid {
		background-color: white;
	}
	#app .page .side .el-menu-item.is-active.title-title {
		border-color: transparent!important;
	}
</style>