package charts.series.dots {
	
	import flash.display.Sprite;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.AntiAliasType;
	import mx.controls.Text;
	import mx.states.SetProperty
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.external.ExternalInterface;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import string.Utils;
	//import mx.managers.CursorManager;
	//import mx.controls.Label;
	
	public class Tag extends Sprite {
		
		public var _x:Number;
		public var _y:Number;
		public var xAdj:Number = 0;
		public var yAdj:Number = 0;
		public var radius:Number;
		public var angle:Number;
		
		private var link:String;
		protected var on_click_text:String;
		private var index:Number;
		protected var right_axis:Boolean;
		private var myLabel:TextField;
		private var clickPanel:Sprite;
		
		public function Tag( style:Object ) {

			this.myLabel = new TextField();
			this.addChild(this.myLabel);
			
			this._x = style.x;
			this._y = style.y;
			this.radius = style.radius;
			this.angle = style.angle;
			this.right_axis = (style.axis == 'right');

			this.myLabel.text = this.replace_magic_values(style.text);
			this.myLabel.autoSize = "left";
			this.myLabel.alpha = style.alpha;
			this.myLabel.border = style.border;
			if (style.background != null) {
				this.myLabel.background = true;
				this.myLabel.backgroundColor = Utils.get_colour(style.background);
			}

			var fmt:TextFormat = new TextFormat();
			if (style.rotate != 0) {
				fmt.font = "spArial";
				this.myLabel.embedFonts = true;
				this.myLabel.antiAliasType = AntiAliasType.ADVANCED;
			}
			else {
				fmt.font = style.font;
			}
			fmt.color = style.colour;
			fmt.size = style['font-size'];
			fmt.bold = style.bold;
			fmt.align = "center";
			fmt.italic = style.italic;
			this.myLabel.setTextFormat(fmt);

			// prevents bar cursor but still clickable 
			// Hoping to figure out how to change the cursor
			this.myLabel.selectable = false; 
			this.rotate_and_align(style.rotate, style['align-x'], style['align-y'], style['pad-x'], style['pad-y']);
			
			if ( style['on-click'] )
				this.set_on_click( style['on-click'] );
			this.on_click_text = style['on-click-text'];
			this.on_click_text = this.replace_magic_values(this.on_click_text);
		}
	
		public function rotate_and_align( rotation:Number, xAlign:String, yAlign:String, 
										  xPad:Number, yPad:Number ): void
		{ 
			rotation = rotation % 360;
			if (rotation < 0) rotation += 360;
			this.rotation = rotation;
			
			// NOTE: Calculations only work for 0, 90, 180, 270 and 360 at the moment
			//       Hopefully I can figure out the calculations for the other angles :(
			var myright:Number = this.width * Math.cos(rotation * Math.PI / 180);
			
			var myleft:Number = this.height * Math.cos((90 - rotation) * Math.PI / 180);
			var mytop:Number = this.height * Math.sin((90 - rotation) * Math.PI / 180);
			var mybottom:Number = this.width * Math.sin(rotation * Math.PI / 180);
			
			if (xAlign == "right")
			{
				switch (rotation)
				{
					case 0: 	this.xAdj = 0; 
								break;
					case 90: 	this.xAdj = this.width; 
								break;
					case 180: 	this.xAdj = this.width; 
								break;
					case 270: 	this.xAdj = 0; 
								break;
				}
				this.xAdj = this.xAdj + xPad;
			}
			else if (xAlign == "left")
			{
				switch (rotation)
				{
					case 0: 	this.xAdj = -this.width; 
								break;
					case 90: 	this.xAdj = 0; 
								break;
					case 180: 	this.xAdj = 0; 
								break;
					case 270: 	this.xAdj = -this.width; 
								break;
				}
				this.xAdj = this.xAdj - xPad;
			}
			else
			{
				// default to align center
				switch (rotation)
				{
					case 0: 	this.xAdj = -this.width / 2; 
								break;
					case 90: 	this.xAdj = this.width / 2; 
								break;
					case 180: 	this.xAdj = this.width / 2; 
								break;
					case 270: 	this.xAdj = -this.width / 2; 
								break;
				}
			}

			if (yAlign == "center")
			{
				switch (rotation)
				{
					case 0: 	this.yAdj = - this.height / 2; 
								break;
					case 90: 	this.yAdj = - this.height / 2; 
								break;
					case 180: 	this.yAdj = this.height / 2; 
								break;
					case 270: 	this.yAdj = this.height / 2; 
								break;
				}
			}
			else if (yAlign == "below") 
			{
				switch (rotation)
				{
					case 0: 	this.yAdj = 0; 
								break;
					case 90: 	this.yAdj = 0; 
								break;
					case 180: 	this.yAdj = this.height; 
								break;
					case 270: 	this.yAdj = this.height; 
								break;
				}
				this.yAdj = this.yAdj + yPad;
			}
			else
			{
				// default to align above
				switch (rotation)
				{
					case 0: 	this.yAdj = - this.height; 
								break;
					case 90: 	this.yAdj = - this.height; 
								break;
					case 180: 	this.yAdj = 0; 
								break;
					case 270: 	this.yAdj = 0; 
								break;
				}
				this.yAdj = this.yAdj - yPad;
			}
		}

		private function replace_magic_values( t:String ): String {
			if (t != null) {
				var regex:RegExp = /#x#/g;
				t = t.replace(regex, NumberUtils.formatNumber(this._x));
				regex = /#y#/g;
				t = t.replace('#y#', NumberUtils.formatNumber(this._y));
				t = DateUtils.replace_magic_values(t, this._x);
				regex = /#ygmdate/g;
				t = t.replace(regex, '#gmdate');
				regex = /#ydate/g;
				t = t.replace('#ydate', '#date');
				t = DateUtils.replace_magic_values(t, this._y);
			}
			return t;
		}
		
		public function set_on_click( s:String ):void {
			// Draw an invisible click panel
			this.clickPanel = new Sprite();
			this.clickPanel.graphics.lineStyle(0, 0, 0);
			this.clickPanel.graphics.beginFill(0, 0);
			this.clickPanel.graphics.drawRect(0, 0, this.myLabel.width, this.myLabel.height);
			this.clickPanel.graphics.endFill();
			this.clickPanel.buttonMode = true;
			this.clickPanel.useHandCursor = true;
			this.addChild(this.clickPanel);
			this.link = s;
			// weak references so the garbage collector will kill it:
			this.clickPanel.addEventListener(MouseEvent.MOUSE_UP, this.mouseUp, false, 0, true);
		}
		
		private function mouseUp(event:Event):void {
			
			if ( this.link.substring(0, 6) == 'trace:' ) {
				// for the test JSON files:
				tr.ace( this.link );
			}
			else if ( this.link.substring(0, 5) == 'http:' )
				this.browse_url( this.link );
			else
				ExternalInterface.call( this.link, this._x );
		}
			
		private function browse_url( url:String ):void {
			var req:URLRequest = new URLRequest(this.link);
			try
			{
				navigateToURL(req);
			}
			catch (e:Error)
			{
				trace("Error opening link: " + this.link);
			}
		}

		public function resize( sc:ScreenCoordsBase ): void {
			// adjust by 2 for the offset between the textfield border and 
			// where text actually is
			if (isNaN(this.radius) || isNaN(this.angle))
			{
				this.x = sc.get_x_from_val( this._x ) + this.xAdj;
				this.y = sc.get_y_from_val( this._y, this.right_axis ) + this.yAdj;
			}
			else
			{
				if (isNaN(this.radius) || isNaN(this.angle))
				{
					this.visible = false;
				}
				else
				{
					this.x = sc.get_center_x() + this.radius * Math.cos(this.angle) + this.xAdj;
					this.y = sc.get_center_y() + this.radius * Math.sin(this.angle) + this.yAdj;
				}
			}
			
		}
	}
}