package charts {
	import charts.series.Element;
	import charts.series.dots.PointDotBase;
	import charts.series.dots.Point;
	import string.Utils;
	import flash.display.BlendMode;
	import flash.geom.Point;
	import flash.display.Sprite;
	import flash.events.TimerEvent;
	import charts.series.dots.DefaultDotProperties;

	//public class Area extends LineBase {
	public class Area extends Line {
		private var fill_colour:Number;
		private var fill_alpha:Number;
		private var save_sc:ScreenCoordsBase;
		
		public function Area( json:Object ) {
			super(json);
			
			var fill:String;
			if (json.fill)
				fill = json.fill
			else if (json.colour) 
				fill = json.colour
			else
				fill = "#3030d0";
				
			this.fill_colour = string.Utils.get_colour(fill);
			this.fill_alpha = this.props.has('fill-alpha') ? this.props.get('fill-alpha') : 0.6;
				
			
			/*
			this.style = {
				values:			[],
				width:			2,
				colour:			'#3030d0',
				fill:			'#3030d0',
				text:			'',		// <-- default not display a key
				'dot-size':		5,
				'font-size':	10,
				'fill-alpha':	0.6,
				'line-style':	new LineStyle( json['line-style'] ),
				loop:			false,		// <-- for radar charts
				
				// default dot:
				// HACK: fix this (remove the merge below)
				'--dot-style':	null
			};
			
			object_helper.merge_2( json, this.style );

			// hack:
			this.style['--dot-style'] = new DefaultDotProperties(
				json['dot-style'], '#val#', this.style.colour);
			
			if( this.style.fill == '' )
				this.style.fill = this.style.colour;
			
			this.style.colour = string.Utils.get_colour( this.style.colour );
			
			this.style.fill = string.Utils.get_colour( this.style.fill );
		
			this.key = style.text;
			this.font_size = style['font-size'];
			this.values = style['values'];
			this.add_values();
			
			//
			// so the mask child can punch a hole through the line
			//
			this.blendMode = BlendMode.LAYER;
			*/
		}
		
		protected override function timedFunction(eventArgs:TimerEvent): void {
			if ( this.still_animating() ) {
				this.draw_line();
			}
			else {
				this.on_show_timer.stop();
				this.draw_area(null);
			}
		}

		public override function resize(sc:ScreenCoordsBase):void {
			var drawArea:Boolean = !this.on_show_start;
			this.save_sc = sc;
			this.graphics.clear();
			// now draw the line + hollow dots
			super.resize(sc);
			if ( drawArea )
				this.draw_area(sc);
		}
		
		protected function draw_area(sc:ScreenCoordsBase):void {
			var last:Element;
			var first:Boolean = true;
			var tmp:Sprite;
			
			sc = (sc != null) ? sc : this.save_sc;
			
			for ( var i:Number = 0; i < this.numChildren; i++ ) {
				
				tmp = this.getChildAt(i) as Sprite;
				
				// filter out the masks
				if( tmp is Element ) {
					
					var e:Element = tmp as Element;
					
					if( first )
					{
						
						first = false;
						
						if (this.props.has('loop') && this.props.get('loop'))
						{
							// assume we are in a radar chart
							this.graphics.moveTo( e.x, e.y );
						}
						else
						{
							// draw line from Y=0 up to Y pos
							this.graphics.moveTo( e.x, sc.get_y_bottom(false) );
						}
						
						//
						// TO FIX BUG: you must do a graphics.moveTo before
						//             starting a fill:
						//
						this.graphics.lineStyle(0,0,0);
						this.graphics.beginFill( this.fill_colour, this.fill_alpha );
						
						if (!this.props.has('loop') || !this.props.get('loop'))
							this.graphics.lineTo( e.x, e.y );
						
					}
					else
					{
						this.graphics.lineTo( e.x, e.y );
						last = e;
					}
				}
			}
			
			if ( last != null ) {
				if (!this.props.has('loop') || !this.props.get('loop')) {
					this.graphics.lineTo( last.x, sc.get_y_bottom(false) );
				}
			}
			

			this.graphics.endFill();
		}
		
	}
}