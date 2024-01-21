<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title') || $check_field('add','title') || $check_field('set','title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题" prop="title">
					<el-input id="title" v-model="form['title']" placeholder="请输入标题"
							  v-if="user_group === '管理员' || (form['inform_students_id'] && $check_field('set','title')) || (!form['inform_students_id'] && $check_field('add','title'))" :disabled="disabledObj['title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title')">{{form['title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student') || $check_field('add','student') || $check_field('set','student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生" prop="student">
						<el-select v-if="user_group === '管理员' || (form['inform_students_id'] && $check_field('set','student')) || (!form['inform_students_id'] && $check_field('add','student'))" id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
							<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','student')" id="student" v-model="form['student']" :disabled="true">
							<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','video_file_') || $check_field('add','video_file_') || $check_field('set','video_file_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="视频文件" prop="video_file_">
					<el-upload v-if="user_group === '管理员' || (form['inform_students_id'] && $check_field('set','video_file_')) || (!form['inform_students_id'] && $check_field('add','video_file_'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_video_file_" :limit="1" accept="video/ogg,video/mp4,video/webm">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','video_file_')">
						<el-button type="primary" @click="download(form['video_file_'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','enclosure') || $check_field('add','enclosure') || $check_field('set','enclosure')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="附件" prop="enclosure">
					<div v-if="disabledObj['enclosure_isDisabled']">
						<div v-if="$check_field('get','enclosure')">
							<el-button type="primary" @click="$download($fullUrl(form['enclosure']),form['enclosure'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['inform_students_id'] && $check_field('set','enclosure')) || (!form['inform_students_id'] && $check_field('add','enclosure'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_enclosure" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','enclosure')">
							<el-button type="primary" @click="$download($fullUrl(form['enclosure']),form['enclosure'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','notice_content') || $check_field('add','notice_content') || $check_field('set','notice_content')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="通知内容" prop="notice_content">
					<quill-editor v-model.number="form['notice_content']"
						v-if="user_group === '管理员' || (form['inform_students_id'] && $check_field('set','notice_content')) || (!form['inform_students_id'] && $check_field('add','notice_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','notice_content')" v-html="form['notice_content']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "inform_students_id",
				url_add: "~/api/inform_students/add?",
				url_set: "~/api/inform_students/set?",
				url_get_obj: "~/api/inform_students/get_obj?",
				url_upload: "~/api/inform_students/upload?",

				query: {
					"inform_students_id": 0,
				},

				form: {
					"title":'', // 标题
					"student": 0, // 学生
					"video_file_":'', // 视频文件
					"enclosure":'', // 附件
					"notice_content":'', // 通知内容
					"inform_students_id": 0, // ID

				},
				disabledObj:{
					"title_isDisabled": false,
					"student_isDisabled": false,
					"video_file__isDisabled": false,
					"enclosure_isDisabled": false,
					"notice_content_isDisabled": false,
				},
				// 用户列表
				list_user_student: [],
			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student() {
                // if(this.user_group !== "管理员" && this.form["student"] === 0) {
                //     this.form["student"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_student = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_student(id){
				var obj = this.list_user_student.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 上传视频文件
			 * @param {Object} param视频参数
			 */
			upload_video_file_(param){
				this.uploadFile(param.file, "video_file_");
			},
			/**
			 * 上传附件
			 * @param {Object} param文件参数
			 */
			upload_enclosure(param){
				this.uploadFile(param.file, "enclosure");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生":
							if(param["student"] > 0){
								param["student"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/inform_students/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/inform_students/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/inform_students/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/inform_students/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/inform_students/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
