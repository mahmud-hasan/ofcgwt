package charts {
	import flash.display.Sprite;
	import flash.geom.Point;
	import string.Utils;
	
	public class Shape extends Base {
		
		private var style:Object;
		
		public function Shape( json:Object )
		{
			this.style = {
				points:				[],
				colour:				'#808080',
				alpha:				0.5,
				'line-alpha':		0,
				width:				0,
				text:				'',   // default to no key
				'font-size':		12
			};
			
			object_helper.merge_2( json, this.style );
			
			this.style.colour		= string.Utils.get_colour( this.style.colour );
			if (this.style["line-colour"] == null) 
				this.style["line-colour"] = this.style.colour
			else
				this.style["line-colour"] = string.Utils.get_colour( this.style.colour );

			this.colour		= this.style.colour;
			this.key		= style.text;
			this.font_size	= style['font-size'];

			for each ( var val:Object in json.values )
				this.style.points.push( new flash.geom.Point( val.x, val.y ) );
		}
		
		public override function resize( sc:ScreenCoordsBase ): void {
			
			this.graphics.clear();
			//this.graphics.lineStyle( this.style.width, this.style.colour );
			this.graphics.lineStyle( this.style.width, this.style["line-colour"], this.style["line-alpha"] );
			
			var moved:Boolean = false;
			
			for each( var val:Object in this.style.values ) {
				if (val.x == null ) {
					this.graphics.endFill();
					moved = false;
				}
				else
				{
					var xVal:Number = (val.x is Number) ? sc.get_x_from_val(val.x) : (val.x == "max") ? sc.right : sc.left;
					var yVal:Number = (val.y is Number) ? sc.get_y_from_val(val.y) : (val.y == "max") ? sc.top : sc.bottom;
					if( !moved ) {
						this.graphics.beginFill( this.style.colour, this.style.alpha );
						this.graphics.moveTo( xVal, yVal );
					}
					else 
						this.graphics.lineTo( xVal, yVal );
					
					moved = true;
				}
			}
			
			this.graphics.endFill();
		}
	}
	
}
