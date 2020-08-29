<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function(){
	$('.mycart').hover(
			function(){
				$('#div_mycart').show('slow');
			},
			function(){
				$('#div_mycart').hide('slow');
			}
		);
});


</script>
<div class="navbar">
	<ul>
		<li><a href="">首页</a></li>
	</ul>
	<div class="mycart">
		<dl>
			<dt>
				<a href="cart.jsp">购物车<b name="mycart_count">0</b>件
				</a>
			</dt>
			<dd>
				<a href="cart.jsp">去结算</a>
			</dd>
		</dl>

		<!--购物车浮动div 开始-->
		<div class="shopping" id='div_mycart' style='display:none;'></div>
		<!--购物车浮动div 结束-->
		<script type='text/html' id='cartTemplete'>
			<dl class="cartlist">
				{{each goodsData as goods index}}
				<dd id="site_cart_dd_{{index}}">
					<div class="pic f_l"><img width="55" height="55" src="{{data['img']}}"></div>
					<h3 class="title f_l"><a href="{{data['goods_id']}}">{{data['name']}}</a></h3>
					<div class="price f_r t_r">
						<b class="block">￥{{data['sell_price']}} x {{data['count']}}</b>
						<input class="del" type="button" value="删除" onclick="removeCart('');$('#site_cart_dd_{{item}}').hide('slow');" />
					</div>
				</dd>
				{{/each}}
				<dd class="static"><span>共<b name="mycart_count">0</b>件商品</span>金额总计：<b name="mycart_sum">￥0</b></dd>
				<dd class="static">
						<label class="btn_orange"><input type="button" value="去购物车结算" onclick="" /></label>
				</dd>
			</dl>
			</script>
		<!--购物车模板 结束-->

	</div>
</div>