<template>
<view class="content">
	<view :style='{"minHeight":"100vh","width":"100%","padding":"20rpx 0 0","background":"#fff","height":"auto"}'>
		<swiper :style='{"padding":"0px ","boxShadow":"inset 0px 0px 0px 0px #f4ead8","borderColor":"#d7a37a","outline":"0px solid #bbb","margin":"0px","borderRadius":"0px","background":"#fff","borderWidth":"0px","width":"calc(100% - 0px)","borderStyle":"solid","height":"480rpx"}' class="swiper" :indicator-dots='false' :autoplay='true' :circular='false' indicator-active-color='#000000' indicator-color='rgba(0, 0, 0, .3)' :duration='500' :interval='5000' :vertical='false'>
			<swiper-item :style='{"width":"calc(100% - 40rpx)","margin":"0 auto","position":"relative","borderRadius":"20rpx","background":"none","height":"480rpx"}' v-for="(swiper,index) in swiperList" :key="index" @tap="onSwiperTap(swiper)">
				<image :style='{"width":"calc(100% - 40rpx)","margin":"0px auto","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"480rpx"}' mode="aspectFill" :src="baseUrl+swiper.img"></image>
				<view v-if="false" :style='{"padding":"0 20rpx 32rpx","color":"#333","left":"20rpx","textAlign":"center","background":"rgba(255,255,255,.6)","bottom":"0px","width":"calc(100% - 40rpx)","lineHeight":"60rpx","fontSize":"28rpx","position":"absolute"}'>{{ swiper.title }}</view>
			</swiper-item>
		</swiper>

		<view class="cu-bar bg-white search" :style="[{top:CustomBar + 'px'}]">
			<picker v-if="queryList.length>1" mode="selector" :range="queryList" range-key="queryName" :value="queryIndex" @change="queryChange" style="padding-left: 20upx;">
				<view><image style="width: 20upx;height: 33upx;" src="../../static/center/to.png"></image></view>
			</picker>
			<view v-if="queryIndex==0" class="search-form round">
				<text class="cuIcon-search"></text>
				<input v-model="searchForm.shengxianchaoshishangpinmingcheng" type="text" placeholder="商品名称" ></input>
			</view>
			<view v-if="queryIndex==0" class="action">
				<button @tap="onPageTap('shengxianchaoshi')" class="cu-btn shadow-blur round">搜索</button>
			</view>
		</view>

		<!-- menu -->
		<view v-if="true" class="menu" :style='{"padding":"40rpx 0","boxShadow":"0px 18rpx 8rpx -6rpx #ccc","margin":"48rpx auto","borderColor":"#21d5ae","display":"flex","outline":"0px solid #ccc","borderRadius":"8rpx","flexWrap":"wrap","background":"url(http://codegen.caihongy.cn/20230306/99f6899628694fa99d05c1e3e0b04634.png) no-repeat right bottom / 100% 100%","borderWidth":"0px","width":"calc(100% - 40rpx)","borderStyle":"dashed dashed dashed dashed","height":"auto"}'>
            <block v-for="item in menuList" v-bind:key="item.roleName">
                <block v-if="role==item.roleName" v-bind:key="index" v-for=" (menu,index) in item.frontMenu">
                    <block v-bind:key="sort" v-for=" (child,sort) in menu.child">
                        <block v-bind:key="sort2" v-for=" (button,sort2) in child.buttons">
                            <view :style='{"width":"25%","padding":"20rpx 0","margin":"0","borderRadius":"100%","background":"none","height":"auto"}' class="menu-list" v-if="button=='查看' && child.tableName!='yifahuodingdan' && child.tableName!='yituikuandingdan' &&child.tableName!='yiquxiaodingdan' && child.tableName!='weizhifudingdan' && child.tableName!='yizhifudingdan' && child.tableName!='yiwanchengdingdan' " @tap="onPageTap2('../'+child.tableName+'/list')">
                                <view class="iconarr" :class="child.appFrontIcon" :style='{"padding":"0px","margin":"0px auto","color":"#333","borderRadius":"100%","background":"none","display":"block","width":"64rpx","lineHeight":"64rpx","fontSize":"64rpx","height":"64rpx"}'></view>
                                <view :style='{"padding":"0","margin":"12rpx auto 0","color":"#333","textAlign":"center","width":"100%","lineHeight":"28rpx","fontSize":"28rpx"}'>{{child.menu.split("列表")[0]}}</view>
                            </view>
                        </block>
                    </block>
                </block>
            </block>
		</view>
		<!-- menu -->
		
		

		<!-- 商品推荐 -->
		<view class="listBox recommend">
			<view v-if="false && 1 == 1" class="idea recommendIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
			
			<view class="title" :style='{"padding":"0 92rpx","boxShadow":"0px 0px 0px rgba(0,0,0,.1)","margin":"80rpx auto 40rpx","overflow":"hidden","borderRadius":"0","textAlign":"center","background":"url(http://codegen.caihongy.cn/20230306/9525974a4919488db3193cbaa0e77d76.png) no-repeat left top / auto 100%,url(http://codegen.caihongy.cn/20230306/824b6c82b43b4aefb17550fdfbf82251.png) no-repeat right top / auto 100%,url(http://codegen.caihongy.cn/20230306/e447245591e645419e37f99a09a93bc0.png) repeat-x center top / auto 100%","width":"calc(90% - 40rpx)","height":"84rpx"}'>
				<view :style='{"padding":"0 0px","boxShadow":"0px 0px 0px rgba(0,0,0,.2)","margin":"0","color":"#8f8adb","alignItems":"flex-end","display":"flex","justifyContent":"center","borderRadius":"0","background":"none","width":"auto","fontSize":"36rpx","fontWeight":"600","height":"84rpx"}'>水果超市推荐</view>
			</view>
			
			<view v-if="false && 1 == 2" class="idea recommendIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
			
			<!-- 样式1 -->
			<view class="list-box style1" :style='{"padding":"0px","boxShadow":"0 0px 0px rgba(0,0,0,.1)","margin":"0 auto","borderColor":"#21d5ae","display":"flex","justifyContent":"space-between","borderRadius":"0px","flexWrap":"wrap","background":"none","borderWidth":"0px 0px","width":"calc(100% - 0px)","borderStyle":"dashed","height":"auto"}'>
				<view @tap="onDetailTap('shengxianchaoshi',product.id)" v-for="(product,index) in shengxianchaoshilist" :key="index" class="list-item" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"40rpx 3% 40rpx","borderColor":"#f0d9da #c8d9eb #f0d9da","textAlign":"center","display":"flex","borderRadius":"40rpx","flexWrap":"wrap","background":"#fff","borderWidth":"2rpx","width":"44%","position":"relative","borderStyle":"solid","height":"auto"}'>
					<view :style='{"padding":"0px 20rpx","margin":"0 auto","whiteSpace":"nowrap","overflow":"hidden","color":"#333","textAlign":"center","background":"none","width":"96%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis","order":"2"}' class="list-item-title ">{{product.shangpinmingcheng}}</view>
					<image :style='{"minHeight":"260rpx","padding":"0","margin":"16rpx auto 0px","objectFit":"cover","borderRadius":"20rpx","display":"block","width":"100%","height":"auto","order":"1"}' class="list-item-image" mode="aspectFill" v-if="product && product.tupian && product.tupian.substring(0,4)=='http'" :src="product.tupian" @error="imageError"></image>
						<image :style='{"minHeight":"260rpx","padding":"0","margin":"16rpx auto 0px","objectFit":"cover","borderRadius":"20rpx","display":"block","width":"100%","height":"auto","order":"1"}' class="list-item-image" mode="aspectFill" v-else :src="product && product.tupian?baseUrl+product.tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
					<view :style='{"padding":"0 32rpx 0 8rpx","boxShadow":"0px 6rpx 6rpx -6rpx #999","margin":"0 auto","color":"#c00","borderRadius":"40rpx","textAlign":"center","background":"radial-gradient(circle, rgba(255,255,255,1) 0%, rgba(249,236,236,1) 100%),#f9ecec","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","fontWeight":"700","order":"3"}' class="list-item-pirce">￥{{product.price}}</view>
				</view>
			</view>
			
			
			
			  
			
			
			  
			
			  
			<view v-if="false && 1 == 3" class="idea recommendIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
		</view>
		<!-- 商品推荐 -->
		
		

		<!-- 商品列表 -->
														<view class="listBox list">
			<view v-if="false && 1 == 1" class="idea listIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
		  
			<view class="title" :style='{"padding":"0 92rpx","boxShadow":"0px 0px 0px rgba(0,0,0,.1)","margin":"0 auto 0px","overflow":"hidden","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20230306/9525974a4919488db3193cbaa0e77d76.png) no-repeat left top / auto 100%,url(http://codegen.caihongy.cn/20230306/824b6c82b43b4aefb17550fdfbf82251.png) no-repeat right top / auto 100%,url(http://codegen.caihongy.cn/20230306/e447245591e645419e37f99a09a93bc0.png) repeat-x center top / auto 100%","display":"flex","width":"calc(90% - 40rpx)","position":"relative","justifyContent":"space-between","height":"84rpx"}'>
				<view :style='{"padding":"0","boxShadow":"0px 0px 0px rgba(0,0,0,.2)","margin":"0px auto 0","color":"#8f8adb","alignItems":"flex-end","textAlign":"center","display":"flex","minWidth":"240rpx","justifyContent":"center","borderRadius":"0px","background":"none","width":"calc(100% - 0px)","fontSize":"36rpx","fontWeight":"600","height":"84rpx"}'>水果超市</view>
				<text :style='{"padding":"0 20rpx 0 0","margin":"0px 60rpx 0 0","color":"#555","alignItems":"center","background":"none","display":"flex","fontSize":"28rpx","position":"absolute","right":"20rpx","height":"84rpx"}' @tap="onPageTap('shengxianchaoshi')">查看更多</text>
			</view>
			
			<view v-if="false && 1 == 2" class="idea listIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
			
		  		  
		  		  
		  		  
		  		  
		  		  
		  		  <!-- 样式6 -->
		  <view class="list-box style6" :style='{"width":"100%","padding":"24rpx","margin":"40rpx 0 0","height":"auto"}'>
			<view v-if="homeshengxianchaoshilist.length > 0" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[0].id)" class="box box1" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"0 0 40rpx","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","borderWidth":"2rpx","width":"100%","position":"relative","borderStyle":"solid","height":"auto"}'>
			  			                			  			                			  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","color":"#333","textAlign":"center","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[0].shangpinmingcheng}}</view>
			  			  			                			  			                			  			  			  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[0] && homeshengxianchaoshilist[0].tupian && homeshengxianchaoshilist[0].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[0].tupian" @error="imageError"></image>
							  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[0] && homeshengxianchaoshilist[0].tupian?baseUrl+homeshengxianchaoshilist[0].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
			                			  			                			  			                			  			                			  			                			  			                			  			                			  			</view>
			<view class="list-item" :style='{"width":"100%","margin":"0 0 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
			  <view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
				<view v-if="homeshengxianchaoshilist.length > 1" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[1].id)" class="box box2" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"0 0 40rpx","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","borderWidth":"2rpx","width":"100%","position":"relative","borderStyle":"solid","height":"auto"}'>
				  				                    				  				                    				  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","color":"#333","textAlign":"center","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[1].shangpinmingcheng}}</view>
				  				  				                    				  				                    				  				  				  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[1] && homeshengxianchaoshilist[1].tupian && homeshengxianchaoshilist[1].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[1].tupian" @error="imageError"></image>
					  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[1] && homeshengxianchaoshilist[1].tupian?baseUrl+homeshengxianchaoshilist[1].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				</view>
				<view v-if="homeshengxianchaoshilist.length > 2" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[2].id)" class="box box3" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"0 0 40rpx","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","borderWidth":"2rpx","width":"100%","position":"relative","borderStyle":"solid","height":"auto"}'>
				  				                    				  				                    				  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","color":"#333","textAlign":"center","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[2].shangpinmingcheng}}</view>
				  				  				                    				  				                    				  				  				  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"240rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[2] && homeshengxianchaoshilist[2].tupian && homeshengxianchaoshilist[2].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[2].tupian" @error="imageError"></image>
					  <image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"240rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[2] && homeshengxianchaoshilist[2].tupian?baseUrl+homeshengxianchaoshilist[2].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				</view>
			  </view>
			  <view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
				<view v-if="homeshengxianchaoshilist.length > 3" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[3].id)" class="box box4" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"0 0 40rpx","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","borderWidth":"2rpx","width":"100%","position":"relative","borderStyle":"solid","height":"auto"}'>
				  				                    				  				                    				  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","color":"#333","textAlign":"center","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[3].shangpinmingcheng}}</view>
				  				  				                    				  				                    				  				  				  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[3] && homeshengxianchaoshilist[3].tupian && homeshengxianchaoshilist[3].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[3].tupian" @error="imageError"></image>
					  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"300rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[3] && homeshengxianchaoshilist[3].tupian?baseUrl+homeshengxianchaoshilist[3].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				</view>
				<view v-if="homeshengxianchaoshilist.length > 4" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[4].id)" class="box box5" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"0","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","borderWidth":"2rpx","width":"100%","position":"relative","borderStyle":"solid","height":"auto"}'>
				  				                    				  				                    				  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","color":"#333","textAlign":"center","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[4].shangpinmingcheng}}</view>
				  				  				                    				  				                    				  				  				  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"240rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[4] && homeshengxianchaoshilist[4].tupian && homeshengxianchaoshilist[4].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[4].tupian" @error="imageError"></image>
					  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"240rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[4] && homeshengxianchaoshilist[4].tupian?baseUrl+homeshengxianchaoshilist[4].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				                    				  				</view>
			  </view>
			</view>
			<view v-if="homeshengxianchaoshilist.length > 5" @tap="onDetailTap('shengxianchaoshi',homeshengxianchaoshilist[5].id)" class="box box6" :style='{"width":"100%","padding":"0","margin":"0","position":"relative","display":"none","height":"auto"}'>
			  			                			  			                			  <view v-if="true" :style='{"padding":"0 20rpx","margin":"0","backgroundColor":"rgba(0,0,0,.3)","color":"#fff","textAlign":"center","left":"0","bottom":"0","width":"100%","lineHeight":"64rpx","fontSize":"28rpx","position":"absolute","zIndex":"1"}' class="title ">{{homeshengxianchaoshilist[5].shangpinmingcheng}}</view>
			  			  			                			  			                			  			  			  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshengxianchaoshilist[5] && homeshengxianchaoshilist[5].tupian && homeshengxianchaoshilist[5].tupian.substring(0,4)=='http'" :src="homeshengxianchaoshilist[5].tupian" @error="imageError"></image>
					  <image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshengxianchaoshilist[5] && homeshengxianchaoshilist[5].tupian?baseUrl+homeshengxianchaoshilist[5].tupian.split(',')[0]:'../../static/gen/upload.png'" @error="imageError"></image>
			                			  			                			  			                			  			                			  			                			  			                			  			                			  			</view>
		  </view>
		  		  
		  		  
		  		  
		  		  
			<view v-if="false && 1 == 3" class="idea listIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
		</view>
																																		<!-- 商品列表 -->
		
		
		<!-- 新闻资讯 -->
																																						<view class="listBox news">
			<view v-if="false && 1 == 1" class="idea newsIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
			
			<view class="title" :style='{"padding":"0 92rpx","boxShadow":"0px 0px 0px rgba(0,0,0,.1)","margin":"0px auto 0px","overflow":"hidden","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20230306/9525974a4919488db3193cbaa0e77d76.png) no-repeat left top / auto 100%,url(http://codegen.caihongy.cn/20230306/824b6c82b43b4aefb17550fdfbf82251.png) no-repeat right top / auto 100%,url(http://codegen.caihongy.cn/20230306/e447245591e645419e37f99a09a93bc0.png) repeat-x center top / auto 100%","display":"flex","width":"calc(90% - 40rpx)","position":"relative","justifyContent":"space-between","height":"84rpx"}'>
				<view :style='{"padding":"0","boxShadow":"0px 0px 0px rgba(0,0,0,.2)","margin":"0px auto 0","color":"#8f8adb","textAlign":"center","display":"flex","minWidth":"240rpx","justifyContent":"center","borderRadius":"0px","background":"none","width":"calc(100% - 0px)","fontSize":"32rpx","lineHeight":"104rpx","fontWeight":"600","height":"104rpx"}'>商品资讯</view>
				<text :style='{"padding":"0 20rpx 0 0","margin":"0px 60rpx 0 0","color":"#555","alignItems":"center","background":"none","display":"flex","fontSize":"28rpx","position":"absolute","right":"20rpx","height":"84rpx"}' @tap="onPageTap('news')">查看更多</text>
			</view>
			
			<view v-if="false && 1 == 2" class="idea newsIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
			
					  
						
						
		  <!-- 样式4 -->
		  		  <view class="news-box1" :style='{"padding":"0px 24rpx","margin":"60rpx 0 60rpx","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
			<view @tap="onNewsDetailTap(item.id)" v-for="(item,index) in news" :key="index" class="list-item" :style='{"padding":"0px 0","margin":"0 0 40rpx","borderColor":"#fbd341 #eee #fbd341","borderRadius":"0px","flexWrap":"wrap","background":"rgba(255,255,255,1)","borderWidth":"0px","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
			  <view class="list-item-body" :style='{"padding":"20rpx","boxShadow":"inset 0px 0px 112rpx 0px #ecf2f9,0px 12rpx 18rpx -12rpx #ccc","margin":"20rpx 0 0 0","borderColor":"#f0d9da #c8d9eb #f0d9da","borderRadius":"40rpx","background":"none","borderWidth":"2rpx","width":"100%","borderStyle":"solid","height":"auto","order":"2"}'>
				<view :style='{"padding":"0 20rpx","margin":"0","whiteSpace":"nowrap","overflow":"hidden","color":"#8f8adb","width":"100%","lineHeight":"48rpx","fontSize":"32rpx","textOverflow":"ellipsis","fontWeight":"600","order":"2"}' class="title ">{{item.title}}</view>
				<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#666","textAlign":"left","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textIndent":"2em","height":"96rpx"}' class="desc ">{{item.introduction}}</view>
				<view :style='{"padding":"0 20rpx","margin":"0","color":"#999","textAlign":"left","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}' class="time">{{item.addtime.split(" ")[0]}}</view>
			  </view>
			  <image :style='{"width":"100%","margin":"0 auto","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' mode="aspectFill" class="listmpic" :src="item && item.picture?baseUrl+item.picture:''"></image>
			</view>
		  </view>
		  		  
		  <!-- 样式5 -->
		  		  
		  <!-- 样式6 -->
		  		  
		  <!-- 样式7 -->
		  		  
		  <!-- 样式8 -->
		  		  
		  <!-- 样式9 -->
		  			
			<view v-if="false && 1 == 3" class="idea newsIdea" :style='{"padding":"40rpx","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box1"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box2"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box3"></view>
				<view :style='{"width":"20%","background":"#fff","height":"160rpx"}' class="box box4"></view>
			</view>
		</view>
										<!-- 新闻资讯 -->
				

	</view>
</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
				  'cuIcon-same',
				  'cuIcon-deliver',
				  'cuIcon-evaluate',
				  'cuIcon-shop',
				  'cuIcon-ticket',
				  'cuIcon-cascades',
				  'cuIcon-discover',
				  'cuIcon-question',
				  'cuIcon-pic',
				  'cuIcon-filter',
				  'cuIcon-footprint',
				  'cuIcon-pulldown',
				  'cuIcon-pullup',
				  'cuIcon-moreandroid',
				  'cuIcon-refund',
				  'cuIcon-qrcode',
				  'cuIcon-remind',
				  'cuIcon-profile',
				  'cuIcon-home',
				  'cuIcon-message',
				  'cuIcon-link',
				  'cuIcon-lock',
				  'cuIcon-unlock',
				  'cuIcon-vip',
				  'cuIcon-weibo',
				  'cuIcon-activity',
				  'cuIcon-friendadd',
				  'cuIcon-friendfamous',
				  'cuIcon-friend',
				  'cuIcon-goods',
				  'cuIcon-selection'
				],
                role : '',
                menuList: [],
                swiperMenuList:[],
                user: {},
                tableName:'',
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				queryList:[
					{
						queryName:"商品名称",
					},
				],
				queryIndex: 0,
				searchForm:{
					shengxianchaoshishangpinmingcheng:'',
				},
				CustomBar: '0',

				//轮播
				swiperList: [],
				shengxianchaoshilist: [],
				homeshengxianchaoshilist: [],
				news: [],
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
        async onLoad(){
            this.role = uni.getStorageSync("role");
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
            this.tableName = table;
            let menus = menu.list();
            this.menuList = menus;
            this.menuList.forEach((item,key) => {
                if(this.role==item.roleName) {
                    item.frontMenu.forEach((item2,key2) => {
                        if(item2.child[0].buttons.indexOf("查看")>-1) {
                            this.swiperMenuList.push(item2);
                        }
                    })
                }
            })
        },
		async onShow() {
            this.btnColor = this.btnColor.sort(()=> {
                    return (0.5-Math.random());
            });
            let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.page('config', {
				page: 1,
				limit: 5
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
                        title: item.name
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			// 商品资讯
			res = await this.$api.list('news', {
				page: 1,
				limit: 6
			});
			this.news = res.data.list

			// 推荐信息
			if(uni.getStorageSync("userid")!==null) {
				res = await this.$api.recommend2('shengxianchaoshi', {
                    page: 1,
                    limit: 4
                });
			} else {
				res = await this.$api.recommend('shengxianchaoshi', {                                              
                    page: 1,
                    limit: 4
                });
			}
			this.shengxianchaoshilist = res.data.list

			res = await this.$api.list('shengxianchaoshi', {
				page: 1,

				limit: 6
			});
			this.homeshengxianchaoshilist = res.data.list
		},

		methods: {

			//查询条件切换
			queryChange(e) {
				this.queryIndex=e.detail.value;
				this.searchForm.shengxianchaoshishangpinmingcheng="";
			},
			//轮播图跳转
			onSwiperTap(e) {

			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			// 图片加载错误处理
			imageError(e) {
				// 图片加载失败时，设置默认图片
				const image = e.target;
				image.src = '../../static/gen/upload.png';
			},
			onPageTap(tableName){
				if(this.queryIndex==0) {
					uni.setStorageSync('indexQueryCondition',this.searchForm.shengxianchaoshishangpinmingcheng);
					this.searchForm.shengxianchaoshishangpinmingcheng = '';
				}

				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
            onPageTap2(url) {
                uni.setStorageSync("useridTag",0);
                uni.navigateTo({
                    url: url,
                    fail: function() {
                        uni.switchTab({
                            url: url
                        });
                    }
                });
            }
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
