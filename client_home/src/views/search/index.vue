<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_employee_number"
				title="教师职工号"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_college"
				title="教师学院"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_number"
				title="学生学号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_class_number"
				title="学生班号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_college"
				title="学生学院"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_major"
				title="学生专业"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/professional_classification/list', 'get')"
				:list="result_professional_classification_professional_type"
				title="专业分类专业类型"
				source_table="professional_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/curriculum/list', 'get')"
				:list="result_curriculum_course_name"
				title="课程课程名称"
				source_table="curriculum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/curriculum/list', 'get')"
				:list="result_curriculum_professional_type"
				title="课程专业类型"
				source_table="curriculum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_selection_management/list', 'get')"
				:list="result_course_selection_management_course_name"
				title="选课管理课程名称"
				source_table="course_selection_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teaching_task/list', 'get')"
				:list="result_teaching_task_task_time"
				title="教学任务任务时间"
				source_table="teaching_task"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/college_management/list', 'get')"
				:list="result_college_management_college"
				title="学院管理学院"
				source_table="college_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/class_management/list', 'get')"
				:list="result_class_management_class_number"
				title="班级管理班号"
				source_table="class_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/achievement_management/list', 'get')"
				:list="result_achievement_management_student_number"
				title="成绩管理学号"
				source_table="achievement_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/achievement_management/list', 'get')"
				:list="result_achievement_management_class_number"
				title="成绩管理班号"
				source_table="achievement_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/inform_students/list', 'get')"
				:list="result_inform_students_title"
				title="通知学生标题"
				source_table="inform_students"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/inform_the_teacher/list', 'get')"
				:list="result_inform_the_teacher_title"
				title="通知教师标题"
				source_table="inform_the_teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/data_backup/list', 'get')"
				:list="result_data_backup_backup_time"
				title="数据备份备份时间"
				source_table="data_backup"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_teacher_employee_number":[],
			"result_teacher_college":[],
			"result_student_student_number":[],
			"result_student_class_number":[],
			"result_student_college":[],
			"result_student_major":[],
			"result_professional_classification_professional_type":[],
			"result_curriculum_course_name":[],
			"result_curriculum_professional_type":[],
			"result_course_selection_management_course_name":[],
			"result_teaching_task_task_time":[],
			"result_college_management_college":[],
			"result_class_management_class_number":[],
			"result_achievement_management_student_number":[],
			"result_achievement_management_class_number":[],
			"result_inform_students_title":[],
			"result_inform_the_teacher_title":[],
			"result_data_backup_backup_time":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取employee_number
	 */
	get_teacher_employee_number(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "employee_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_employee_number = json.result.list;
			result_teacher_employee_number.map(o => o.title = o['employee_number'])
	  			this.result_teacher_employee_number = result_teacher_employee_number
		 	}
		});
	},
	/**
	 * 获取college
	 */
	get_teacher_college(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "college": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_college = json.result.list;
			result_teacher_college.map(o => o.title = o['college'])
	  			this.result_teacher_college = result_teacher_college
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_student_student_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_number = json.result.list;
			result_student_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_student_number = result_student_student_number
		 	}
		});
	},
	/**
	 * 获取class_number
	 */
	get_student_class_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "class_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_class_number = json.result.list;
			result_student_class_number.map(o => o.title = o['class_number'])
	  			this.result_student_class_number = result_student_class_number
		 	}
		});
	},
	/**
	 * 获取college
	 */
	get_student_college(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "college": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_college = json.result.list;
			result_student_college.map(o => o.title = o['college'])
	  			this.result_student_college = result_student_college
		 	}
		});
	},
	/**
	 * 获取major
	 */
	get_student_major(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "major": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_major = json.result.list;
			result_student_major.map(o => o.title = o['major'])
	  			this.result_student_major = result_student_major
		 	}
		});
	},
	/**
	 * 获取professional_type
	 */
	get_professional_classification_professional_type(){
		this.$get("~/api/professional_classification/get_list?like=0", { page: 1, size: 10, "professional_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_professional_classification_professional_type = json.result.list;
			result_professional_classification_professional_type.map(o => o.title = o['professional_type'])
	  			this.result_professional_classification_professional_type = result_professional_classification_professional_type
		 	}
		});
	},
	/**
	 * 获取course_name
	 */
	get_curriculum_course_name(){
		this.$get("~/api/curriculum/get_list?like=0", { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_curriculum_course_name = json.result.list;
			result_curriculum_course_name.map(o => o.title = o['course_name'])
	  			this.result_curriculum_course_name = result_curriculum_course_name
		 	}
		});
	},
	/**
	 * 获取professional_type
	 */
	get_curriculum_professional_type(){
		this.$get("~/api/curriculum/get_list?like=0", { page: 1, size: 10, "professional_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_curriculum_professional_type = json.result.list;
			result_curriculum_professional_type.map(o => o.title = o['professional_type'])
	  			this.result_curriculum_professional_type = result_curriculum_professional_type
		 	}
		});
	},
	/**
	 * 获取course_name
	 */
	get_course_selection_management_course_name(){
		this.$get("~/api/course_selection_management/get_list?like=0", { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_selection_management_course_name = json.result.list;
			result_course_selection_management_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_selection_management_course_name = result_course_selection_management_course_name
		 	}
		});
	},
	/**
	 * 获取task_time
	 */
	get_teaching_task_task_time(){
		this.$get("~/api/teaching_task/get_list?like=0", { page: 1, size: 10, "task_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_teaching_task_task_time = json.result.list;
			result_teaching_task_task_time.map(o => o.title = o['task_time'])
	  			this.result_teaching_task_task_time = result_teaching_task_task_time
		 	}
		});
	},
	/**
	 * 获取college
	 */
	get_college_management_college(){
		this.$get("~/api/college_management/get_list?like=0", { page: 1, size: 10, "college": this.query.word }, (json) => {
		  if (json.result) {
			var result_college_management_college = json.result.list;
			result_college_management_college.map(o => o.title = o['college'])
	  			this.result_college_management_college = result_college_management_college
		 	}
		});
	},
	/**
	 * 获取class_number
	 */
	get_class_management_class_number(){
		this.$get("~/api/class_management/get_list?like=0", { page: 1, size: 10, "class_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_class_management_class_number = json.result.list;
			result_class_management_class_number.map(o => o.title = o['class_number'])
	  			this.result_class_management_class_number = result_class_management_class_number
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_achievement_management_student_number(){
		this.$get("~/api/achievement_management/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_achievement_management_student_number = json.result.list;
			result_achievement_management_student_number.map(o => o.title = o['student_number'])
	  			this.result_achievement_management_student_number = result_achievement_management_student_number
		 	}
		});
	},
	/**
	 * 获取class_number
	 */
	get_achievement_management_class_number(){
		this.$get("~/api/achievement_management/get_list?like=0", { page: 1, size: 10, "class_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_achievement_management_class_number = json.result.list;
			result_achievement_management_class_number.map(o => o.title = o['class_number'])
	  			this.result_achievement_management_class_number = result_achievement_management_class_number
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_inform_students_title(){
		this.$get("~/api/inform_students/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_inform_students_title = json.result.list;
			result_inform_students_title.map(o => o.title = o['title'])
	  			this.result_inform_students_title = result_inform_students_title
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_inform_the_teacher_title(){
		this.$get("~/api/inform_the_teacher/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_inform_the_teacher_title = json.result.list;
			result_inform_the_teacher_title.map(o => o.title = o['title'])
	  			this.result_inform_the_teacher_title = result_inform_the_teacher_title
		 	}
		});
	},
	/**
	 * 获取backup_time
	 */
	get_data_backup_backup_time(){
		this.$get("~/api/data_backup/get_list?like=0", { page: 1, size: 10, "backup_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_data_backup_backup_time = json.result.list;
			result_data_backup_backup_time.map(o => o.title = o['backup_time'])
	  			this.result_data_backup_backup_time = result_data_backup_backup_time
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_teacher_employee_number();
		this.get_teacher_college();
		this.get_student_student_number();
		this.get_student_class_number();
		this.get_student_college();
		this.get_student_major();
		this.get_professional_classification_professional_type();
		this.get_curriculum_course_name();
		this.get_curriculum_professional_type();
		this.get_course_selection_management_course_name();
		this.get_teaching_task_task_time();
		this.get_college_management_college();
		this.get_class_management_class_number();
		this.get_achievement_management_student_number();
		this.get_achievement_management_class_number();
		this.get_inform_students_title();
		this.get_inform_the_teacher_title();
		this.get_data_backup_backup_time();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_teacher_employee_number();
	  this.get_teacher_college();
	  this.get_student_student_number();
	  this.get_student_class_number();
	  this.get_student_college();
	  this.get_student_major();
	  this.get_professional_classification_professional_type();
	  this.get_curriculum_course_name();
	  this.get_curriculum_professional_type();
	  this.get_course_selection_management_course_name();
	  this.get_teaching_task_task_time();
	  this.get_college_management_college();
	  this.get_class_management_class_number();
	  this.get_achievement_management_student_number();
	  this.get_achievement_management_class_number();
	  this.get_inform_students_title();
	  this.get_inform_the_teacher_title();
	  this.get_data_backup_backup_time();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
