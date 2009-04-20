package charts.series.dots {
	import charts.series.dots.PointDotBase;
	import flash.display.Sprite;
	import flash.display.BlendMode;
	import string.Utils;
	import object_helper;
	
	public class DotShape extends PointDotBase {

		protected var props: Properties;
		
		public function DotShape( index:Number, style:Properties )
		{
			super(0, null);
			
			tr.aces("style.values[0].x = ", style.get('values')[0].x);
				
			// Initialize everything the super(s) would init
			this.right_axis = false;	
			this.is_tip = false;
			this.visible = true;
			this.on_show_animate = false;
			this.on_show = new Properties( {
					type:		"",
					cascade:	3,
					delay:		0
					});
		
			var defaultProperties:Object = { 
								values:     	[],
								colour:			'#808080',
								alpha:			0.5,
								'line-alpha':	0,
								width:			0,
								'font-size':	12 };
			tr.aces("defaultProperties.values[0].x = ", defaultProperties.values[0]);
			tr.aces("style.getObject().values[0].x = ", style.getObject().values[0]);
			object_helper.merge_2(style.getObject(), defaultProperties);
			tr.aces("defaultProperties.values[0].x = ", defaultProperties.values[0]);

			this.props = new Properties(defaultProperties);

			var tmp:Object = this.props.get('values');
			tr.aces("DotShape-values = ", tmp);
			
			// set the _x and _y from the first point
			for each( var val:Object in this.props.get('values') ) {
				this._x = val.x;
				this._y = val.y;
				break;
			}
			this.index = Number.MIN_VALUE;

			tr.aces("in DotShape: x,y = ", this._x, this._y);
			
			this.radius = 0;
			this.tooltip = this.replace_magic_values( this.props.get('tip') );
			
			if ( this.props.has('on-click') )
			this.set_on_click( this.props.get('on-click') );

			this.props.set("colour", string.Utils.get_colour( this.props.get('colour') ) );
			this.props.set("line-colour", string.Utils.get_colour( this.props.get('line-colour') ) );
			
			//
			// TODO: fix this hack
			//
			if ( this.props.has('axis') )
			if ( this.props.get('axis') == 'right' )
					this.right_axis = true;
		}

		public override function resize( sc:ScreenCoordsBase ): void {
			
			this.graphics.clear();
			//this.graphics.lineStyle( this.style.width, this.style.colour );
			this.graphics.lineStyle( this.props.get('width'), this.props.get("line-colour"), 
									 this.props.get("line-alpha") );
			
			// Position this shape at the first x,y and then adjust each point after that
			this.x = sc.get_x_from_val(this._x);
			this.y = sc.get_y_from_val(this._y, this.right_axis);
									 
			var moved:Boolean = false;
			
			for each( var val:Object in this.props.get('values') ) {
				if (val.x == null ) {
					this.graphics.endFill();
					moved = false;
				}
				else
				{
					var xVal:Number = (val.x is Number) ? sc.get_x_from_val(val.x) : (val.x == "max") ? sc.right : sc.left;
					var yVal:Number = (val.y is Number) ? sc.get_y_from_val(val.y) : (val.y == "max") ? sc.top : sc.bottom;
					
					// adjust each point relative to the start point
					xVal = xVal - this.x;
					yVal = yVal - this.y;
					
					if( !moved ) {
						this.graphics.beginFill( this.props.get('colour'), this.props.get('alpha') );
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