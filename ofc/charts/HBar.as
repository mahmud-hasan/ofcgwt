package charts {
	import charts.series.Element;
	import charts.series.bars.Horizontal;
	import string.Utils;
	import global.Global;
	
	public class HBar extends Base {
		
		protected var group:Number;
		protected var style:Object;
		
		public function HBar( json:Object, group:Number ) {
			
			super();
			
			this.style = {
				values:				[],
				colour:				'#3030d0',
				text:				'',		// <-- default not display a key
				'font-size':		12,
				tip:				'#val#'
			};
			
			object_helper.merge_2( json, style );
			
			//this.alpha = Number( vals[0] );
			this.colour = string.Utils.get_colour( style.colour );
			this.key = json.text;
			this.font_size = json['font-size'];
			
			//
			// bars are grouped, so 3 bar sets on one chart
			// will arrange them selves next to each other
			// at each value of X, this.group tell the bar
			// where it is in that grouping
			//
			this.group = group;
			
			this.values = json['values'];
			
			this.style['on-click'] = json['on-click'];
			
			this.add_values();
		}
		
		//
		// called from the base object, in this case the
		// value is the X value of the bar and the index
		// is the Y positiont
		//
		protected override function get_element( index:Number, value:Object ): Element {
			
			var default_style:Object = {
					colour:		this.style.colour,
					tip:		this.style.tip,
					alpha:		this.style.alpha,
					barwidth:	this.style.barwidth,
					'on-click': this.style['on-click']
			};
			
			if( value is Number )
				default_style.top = value;
			else
				object_helper.merge_2( value, default_style );
				
			// our parent colour is a number, but
			// we may have our own colour:
			if( default_style.colour is String )
				default_style.colour = Utils.get_colour( default_style.colour );
			
			return new Horizontal( index, default_style, this.group );
		}
		
		public override function resize( sc:ScreenCoordsBase ): void {
			
			for ( var i:Number = 0; i < this.numChildren; i++ )
			{
				var p:Horizontal = this.getChildAt(i) as Horizontal;
				p.resize( sc );
			}
		}
		
		public override function get_max_x():Number {
			
			var x:Number = 0;
			//
			// count the non-mask items:
			//
			for ( var i:Number = 0; i < this.numChildren; i++ )
				if( this.getChildAt(i) is Horizontal ) {
					
					var h:Horizontal = this.getChildAt(i) as Horizontal;
					x = Math.max( x, h.get_max_x() );
					
				}
	
			return x;
		}
		
		public override function get_min_x():Number {
			return 0;
		}

		public override function closest( x:Number, y:Number ): Object {
			var shortest:Number = Number.MAX_VALUE;
			var ey:Element = null;
			
			for ( var i:Number = 0; i < this.numChildren; i++ )
			{
				var e:Element = this.getChildAt(i) as Element;

				e.is_tip = false;
				
				if( (y > e.y) && (y < e.y+e.height) )
				{
					// mouse is in position 1
					shortest = Math.min( Math.abs( y - e.y ), Math.abs( y - (e.y+e.height) ) );
					ey = e;
					break;
				}
				else
				{
					// mouse is in position 2
					// get distance to left side and right side
					var d1:Number = Math.abs( y - e.y );
					var d2:Number = Math.abs( y - (e.y+e.height) );
					var min:Number = Math.min( d1, d2 );
					if( min < shortest )
					{
						shortest = min;
						ey = e;
					}
				}
			}
			var dx:Number = Math.abs( x - ey.x );
			
			return { element:ey, distance_x:dx, distance_y:shortest };
		}

	}
}
