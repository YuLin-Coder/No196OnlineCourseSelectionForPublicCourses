<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','task_name') || $check_field('add','task_name') || $check_field('set','task_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="任务名称" prop="task_name">
					<el-input id="task_name" v-model="form['task_name']" placeholder="请输入任务名称"
							  v-if="user_group === '管理员' || (form['teaching_task_id'] && $check_field('set','task_name')) || (!form['teaching_task_id'] && $check_field('add','task_name'))" :disabled="disabledObj['task_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','task_name')">{{form['task_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','task_time') || $check_field('add','task_time') || $check_field('set','task_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="任务时间" prop="task_time">
					<el-date-picker :disabled="disabledObj['task_time_isDisabled']" v-if="user_group === '管理员' || (form['teaching_task_id'] && $check_field('set','task_time')) || (!form['teaching_task_id'] && $check_field('add','task_time'))" id="task_time"
						v-model="form['task_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','task_time')">{{form['task_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','designated_teacher') || $check_field('add','designated_teacher') || $check_field('set','designated_teacher')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="指定教师" prop="designated_teacher">
						<el-select v-if="user_group === '管理员' || (form['teaching_task_id'] && $check_field('set','designated_teacher')) || (!form['teaching_task_id'] && $check_field('add','designated_teacher'))" id="designated_teacher" v-model="form['designated_teacher']" :disabled="disabledObj['designated_teacher_isDisabled']">
							<el-option v-for="o in list_user_designated_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','designated_teacher')" id="designated_teacher" v-model="form['designated_teacher']" :disabled="true">
							<el-option v-for="o in list_user_designated_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
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
						<el-upload v-if="user_group === '管理员' || (form['teaching_task_id'] && $check_field('set','enclosure')) || (!form['teaching_task_id'] && $check_field('add','enclosure'))" class="upload-demo" drag
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
			<el-col v-if="user_group === '管理员' || $check_field('get','task_content') || $check_field('add','task_content') || $check_field('set','task_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="任务内容" prop="task_content">
					<el-input type="textarea" id="task_content" v-model="form['task_content']" placeholder="请输入任务内容"
						v-if="user_group === '管理员' || (form['teaching_task_id'] && $check_field('set','task_content')) || (!form['teaching_task_id'] && $check_field('add','task_content'))" :disabled="disabledObj['task_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','task_content')">{{form['task_content']}}</div>
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
				field: "teaching_task_id",
				url_add: "~/api/teaching_task/add?",
				url_set: "~/api/teaching_task/set?",
				url_get_obj: "~/api/teaching_task/get_obj?",
				url_upload: "~/api/teaching_task/upload?",

				query: {
					"teaching_task_id": 0,
				},

				form: {
					"task_name":'', // 任务名称
					"task_time":'', // 任务时间
					"designated_teacher": 0, // 指定教师
					"enclosure":'', // 附件
					"task_content":'', // 任务内容
					"teaching_task_id": 0, // ID

				},
				disabledObj:{
					"task_name_isDisabled": false,
					"task_time_isDisabled": false,
					"designated_teacher_isDisabled": false,
					"enclosure_isDisabled": false,
					"task_content_isDisabled": false,
				},
				// 用户列表
				list_user_designated_teacher: [],
			}
		},
		methods: {
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_designated_teacher() {
                // if(this.user_group !== "管理员" && this.form["designated_teacher"] === 0) {
                //     this.form["designated_teacher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_designated_teacher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_designated_teacher(id){
				var obj = this.list_user_designated_teacher.getObj({"user_id":id});
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
						case "指定教师":
							if(param["designated_teacher"] > 0){
								param["designated_teacher"] = this.user.user_id;
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
        if (this.form["task_time"].indexOf("-")===-1){
          this.form["task_time"] = this.$toTime(parseInt(this.form["task_time"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["task_time"]) > 9999){
					this.form["task_time"] = this.$toTime(parseInt(this.form["task_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/teaching_task/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/teaching_task/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/teaching_task/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/teaching_task/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/teaching_task/view','get');
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
			this.get_list_user_designated_teacher();
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
