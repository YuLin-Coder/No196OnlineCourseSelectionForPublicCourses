<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','course_number') || $check_field('add','course_number') || $check_field('set','course_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程编号" prop="course_number">
					<el-input id="course_number" v-model="form['course_number']" placeholder="请输入课程编号"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','course_number')) || (!form['course_selection_management_id'] && $check_field('add','course_number'))" :disabled="disabledObj['course_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_number')">{{form['course_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','course_name') || $check_field('add','course_name') || $check_field('set','course_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程名称" prop="course_name">
					<el-input id="course_name" v-model="form['course_name']" placeholder="请输入课程名称"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','course_name')) || (!form['course_selection_management_id'] && $check_field('add','course_name'))" :disabled="disabledObj['course_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_name')">{{form['course_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','professional_type') || $check_field('add','professional_type') || $check_field('set','professional_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="专业类型" prop="professional_type">
					<el-input id="professional_type" v-model="form['professional_type']" placeholder="请输入专业类型"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','professional_type')) || (!form['course_selection_management_id'] && $check_field('add','professional_type'))" :disabled="disabledObj['professional_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','professional_type')">{{form['professional_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','suitable_grade') || $check_field('add','suitable_grade') || $check_field('set','suitable_grade')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="适学年级" prop="suitable_grade">
					<el-input id="suitable_grade" v-model="form['suitable_grade']" placeholder="请输入适学年级"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','suitable_grade')) || (!form['course_selection_management_id'] && $check_field('add','suitable_grade'))" :disabled="disabledObj['suitable_grade_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','suitable_grade')">{{form['suitable_grade']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','limited_number_of_people') || $check_field('add','limited_number_of_people') || $check_field('set','limited_number_of_people')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="限定人数" prop="limited_number_of_people">
					<el-input id="limited_number_of_people" v-model="form['limited_number_of_people']" placeholder="请输入限定人数"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','limited_number_of_people')) || (!form['course_selection_management_id'] && $check_field('add','limited_number_of_people'))" :disabled="disabledObj['limited_number_of_people_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','limited_number_of_people')">{{form['limited_number_of_people']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher') || $check_field('add','teacher') || $check_field('set','teacher')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="教师" prop="teacher">
						<el-select v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','teacher')) || (!form['course_selection_management_id'] && $check_field('add','teacher'))" id="teacher" v-model="form['teacher']" :disabled="disabledObj['teacher_isDisabled']">
							<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','teacher')" id="teacher" v-model="form['teacher']" :disabled="true">
							<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student') || $check_field('add','student') || $check_field('set','student')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生" prop="student">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student(form['student']) }}
							<!--<el-input id="business_name" v-model="form['student']" placeholder="请输入学生"-->
							<!--v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','student')) || (!form['course_selection_management_id'] && $check_field('add','student'))" :disabled="disabledObj['student_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student')">{{form['student']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','student')) || (!form['course_selection_management_id'] && $check_field('add','student'))" id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
								<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student')" id="student" v-model="form['student']" :disabled="true">
								<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student" v-model="form['student']" :disabled="disabledObj['student_isDisabled']">
							<el-option v-for="o in list_user_student" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学号" prop="student_number">
					<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学号"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','student_number')) || (!form['course_selection_management_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_number') || $check_field('add','class_number') || $check_field('set','class_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="班号" prop="class_number">
					<el-input id="class_number" v-model="form['class_number']" placeholder="请输入班号"
							  v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','class_number')) || (!form['course_selection_management_id'] && $check_field('add','class_number'))" :disabled="disabledObj['class_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_number')">{{form['class_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_people') || $check_field('add','number_of_people') || $check_field('set','number_of_people')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="人数" prop="number_of_people">
					<el-select id="number_of_people" v-model="form['number_of_people']"
						v-if="user_group === '管理员' || (form['course_selection_management_id'] && $check_field('set','number_of_people')) || (!form['course_selection_management_id'] && $check_field('add','number_of_people'))">
						<el-option v-for="o in list_number_of_people" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','number_of_people')">{{form['number_of_people']}}</div>
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
				field: "course_selection_management_id",
				url_add: "~/api/course_selection_management/add?",
				url_set: "~/api/course_selection_management/set?",
				url_get_obj: "~/api/course_selection_management/get_obj?",
				url_upload: "~/api/course_selection_management/upload?",

				query: {
					"course_selection_management_id": 0,
				},

				form: {
					"course_number":'', // 课程编号
					"course_name":'', // 课程名称
					"professional_type":'', // 专业类型
					"suitable_grade":'', // 适学年级
					"limited_number_of_people":'', // 限定人数
					"teacher": 0, // 教师
					"student": 0, // 学生
					"student_number":'', // 学号
					"class_number":'', // 班号
					"number_of_people":'', // 人数
					"course_selection_management_id": 0, // ID
          "user_id": this.$store.state.user.user_id,

				},
				disabledObj:{
					"course_number_isDisabled": false,
					"course_name_isDisabled": false,
					"professional_type_isDisabled": false,
					"suitable_grade_isDisabled": false,
					"limited_number_of_people_isDisabled": false,
					"teacher_isDisabled": false,
					"student_isDisabled": false,
					"student_number_isDisabled": false,
					"class_number_isDisabled": false,
					"number_of_people_isDisabled": false,
				},
				// 用户列表
				list_user_teacher: [],
				// 用户列表
				list_user_student: [],
				// 用户组
				group_user_student: "",
				//人数选项列表
				list_number_of_people: ['1'],
        limit_times: 0, // 限制次数
        limit_type:0,
        act_limit_times:0,
			}
		},
		methods: {
      /**
       * 提交前验证事件
       * @param {Object} 请求参数
       * @return {String} 验证成功返回null, 失败返回错误提示
       */
      submit_check(param) {
        if(this.act_limit_times>=this.limit_times){
          return "已超过提交次数";
        }
        return null;
      },

      check_limit(){
        /**
         * 提交前验证事件
         * @param {Object} 请求参数
         * @return {String} 验证成功返回null, 失败返回错误提示
         */
        let _this = this;
        if (_this.$store.state.user.user_group==='管理员'){
          _this.limit_times = 999;
        }else {
          _this.$get("~/api/curriculum/get_obj?",
              {"course_number":_this.form.course_number}, function(res) {
            if (res.result && res.result.obj) {
              _this.limit_times=res.result.obj.limit_times;
              _this.limit_type=res.result.obj.limit_type;
              let user_id = _this.$store.state.user.user_id;
              let limit_url = "~/api/course_selection_management/count?course_number="+_this.form.course_number+"&user_id="+user_id;
              if (_this.limit_type === 1){
                let day = _this.$toTime(parseInt((new Date()).getTime()), "yyyy-MM-dd");
                let time = "&create_time_min="+day+" 00:00:00&create_time_max="+day+" 23:59:59"
                limit_url = limit_url +time;
              }
              _this.$get(limit_url ,{}, function(res) {
                _this.act_limit_times = res.result;
              });
            }
          });
        }
      },
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_teacher() {
                // if(this.user_group !== "管理员" && this.form["teacher"] === 0) {
                //     this.form["teacher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_teacher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_teacher(id){
				var obj = this.list_user_teacher.getObj({"user_id":id});
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
			/**
			 * 获取学生用户组
			 */
			async get_group_user_student() {
				this.form["student"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_student = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "教师":
							if(param["teacher"] > 0){
								param["teacher"] = this.user.user_id;
							}
							break;
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
					bl = this.$check_action('/course_selection_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_management/view','get');
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
			this.get_list_user_teacher();
			this.get_list_user_student();
			this.get_group_user_student();
		},
    watch: {
      'form.course_number': {
        handler: function() {
          this.check_limit();
        }
      }
    }
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
