package elements {
   //changed by sumic 19.04.2009 --added background image
   import flash.display.Sprite;
   import string.Utils;
   import flash.net.URLRequest;
   import flash.display.Stage;
   import flash.events.Event;
   import flash.display.Loader;
   import flash.display.LoaderInfo;
   
   public class Background extends Sprite {
      
      private var colour:Number;
      private var img_x:*;
      private var img_y:*;
      //added by sumic
      private var img_width:Number;
      private var img_height:Number;
      private var img_opacity:Number;
      
      //private var mc:MovieClip;
      //private var img_mc:MovieClip;
      
      public function Background( json:Object )
      {
         if( json.bg_colour != undefined )
            this.colour = Utils.get_colour( json.bg_colour );
         else
            this.colour = 0xf8f8d8;      // <-- default to Ivory
         
         if ( json.bg_image != undefined ) {
            this.load_img(json);
         }
      }
      
      
      private function load_img( json:Object):void {
         // added by NetVicious, 05 July, 2007 ++++
         //changed by sumic
         var i:String;
               //both format supported//
         if (typeof (json.bg_image)=='object' &&  json.bg_image.image !=undefined   ) {
            this.img_opacity = (json['bg_image'].opacity != undefined )?  json['bg_image'].opacity :1;
            this.img_x = (json['bg_image'].x != undefined )?  json['bg_image'].x :0;
            this.img_y =(json['bg_image'].y != undefined )? json['bg_image'].y :0;
            if (json['bg_image'].height != undefined) { this.img_height=json['bg_image'].height;}
            if (json['bg_image'].width != undefined) { this.img_width = json['bg_image'].width; }
            i = json['bg_image'].image;
         }else {   
            this.img_opacity = (json.bg_image_opacity != undefined )?  json.bg_image_opacity :1;
            this.img_x = (json.bg_image_x != undefined )?  json.bg_image_x :0;
            this.img_y =(json.bg_image_y != undefined )? json.bg_image_y :0;
            if (json.bg_image_height != undefined) { this.img_height=json.bg_image_height;}
            if (json.bg_image_width != undefined) { this.img_width = json.bg_image_width; }
            i = json.bg_image;
         }
      
         var _loader:Loader = new Loader;
         var request:URLRequest = new URLRequest(i);
         _loader.contentLoaderInfo.addEventListener( Event.INIT , loaded);
         _loader.load(request);
         var Ref:Object = this;
         function loaded(event:Event):void {
            var targetLoader:Loader = Loader(event.target.loader);
            
            if (Ref.img_width!= undefined &&  Ref.img_width>0) {
               this.img_width = Ref.img_width;
               if (Ref.img_width < 1) {
                  targetLoader.width = stage.stageWidth * Ref.img_width;
               }
            }
            if (Ref.img_height != undefined &&  Ref.img_height>0) {
               this.img_height = Ref.img_height;
               if (Ref.img_height < 1) {
                  targetLoader.height = stage.stageHeight * Ref.img_height;
               }
            }
            Ref.positionize(targetLoader, new Square(0, 0, stage.stageWidth, stage.stageHeight) );         
   
            targetLoader.scaleY = targetLoader.scaleX;
            targetLoader.alpha = Ref.img_opacity;
            Ref.addChild(targetLoader);
      
         }
      }
      
      // added by NetVicious, 05 July, 2007
      private   function positionize( mc:Loader, s:Square ):void {
         
         var myX:* =this.img_x;
         var myY: * =this.img_y;
         
         var newX:Number = 0;
         var newY:Number = 0;

         if ( isNaN(myX) ) {
            myX.toLowerCase()
            switch ( myX ) {
               case 'center':
                  newX = (s.width / 2) - (mc.width / 2);
                  break;
               case 'left':
                  newX = s.left;
                  break;
               case 'right':
                  newX = s.right - mc.width;
                  break;
               default:
                  newX = 0;
            }
         } else if ( myX < 0 ) {
            newX = s.right - mc.width - myX;
         } else { newX = s.left + myX; }

         if ( isNaN(myY) ) {
            myY.toLowerCase();
            switch ( myY ) {
               case 'middle':
                  newY = (s.height / 2) - (mc.height / 2);
                  break;
               case 'top':
                  newY = s.top;
                  break;
               case 'bottom':
                  newY = s.bottom - mc.height;
                  break;
               default:
                  newY = 0;
            }
         } else if ( myY < 0 ) {
            newY = s.bottom - mc.height - myY;
         } else { newY = s.top + myY; }

         
         mc.x = newX;
         mc.y = newY;
      }

      public function resize():void {
         this.graphics.beginFill( this.colour );
         this.graphics.drawRect( 0, 0, this.stage.stageWidth, this.stage.stageHeight );
      }
      
      public function die(): void {
   
         this.graphics.clear();
      }
   }
}
