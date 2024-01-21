<template>
	<div v-show="$route.meta.title" class="daohang-a">
		<div class="daohang">
			{{ $route.meta.title }}
		</div>
		<div class="daohang-line"></div>
	</div>
</template>


<script>
	export default {
		computed:{
			
		},
		mounted() {
		},
		methods: {
			
		}
	}
</script>

<style scoped="scoped">
	.daohang {
		display: flex;
		align-items: center;
	}
	.daohang-a {
		padding: 15px;
		background-color: white;
		padding-bottom: 0px;
	}
	.daohang:before {
		content: '';
		height: 24px;
		width: 4px;
		background-color: #6EB6FF;
		display: inline-block;
		margin-right: 10px;
	}
	.daohang-line {
		height: 1px;
		width: 100%;
		background-color: #f1f1f1;
		margin-top: 15px;
	}
</style>
