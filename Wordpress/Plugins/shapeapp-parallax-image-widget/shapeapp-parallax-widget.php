<?php 
    /*
    Plugin Name: Parallax-Image-Widget
    Plugin URI: http://www.shapeapp.se
    Description: Plugin for displaying a widget-image with parallax. Intended to be used with a Page builder plugin.
    Author: Shape App
    Version: 1.0
    Author URI: http://www.shapeapp.se/
    */


     function shapeapp_register_js() {
	    if ( ! is_admin() ) {
	        wp_enqueue_script('skrollr', plugin_dir_url( __FILE__ ) . 'skrollr.min.js', array( 'jquery' ), null, TRUE);  
	        wp_enqueue_script('myjs', plugin_dir_url( __FILE__ ) . 'myjs.js', array( 'jquery' ), null, TRUE);  
	    }
	}
	add_action( 'wp_enqueue_scripts', 'shapeapp_register_js' );

    // Creating the widget 
	class img_parallax_widget extends WP_Widget {

		function __construct() {
			parent::__construct(
				// Base ID of your widget
				'img_parallax_widget', 

				// Widget name will appear in UI
				__('Image parallax', 'img_parallax_widget_domain'), 

				// Widget description
				array( 'description' => __( 'Image widget with parallax effect', 'img_parallax_widget_domain' ), ) 
			);
		}

		// Creating widget front-end
		// This is where the action happens
		public function widget( $args, $instance ) {
			$image = apply_filters( 'widget_title', $instance['image'] );
			$title = isset($instance['title']) ? $instance['title']: '';
			// before and after widget arguments are defined by themes
			echo $args['before_widget'];
			$w_id = $args['widget_id'];
			?>
			<section id="slide-<?php echo $w_id; ?>" class="homeSlide" >
			    <div class="bcg"
			        data-center="background-position: 50% -50px;"
			        data-top-bottom="background-position: 50% -100px;"
			        data-bottom-top="background-position: 50% 0px;"
			        data-anchor-target="#slide-<?php echo $w_id; ?>"
			        style="background-image:url('<?php echo $image; ?>')" >
			            <?php if( $title){ ?>
						<div class="hsContent skrollable skrollable-between" 
							data-bottom-top="opacity: 0;" 
							data-center-top="opacity: 1;" 
							data-anchor-target="#slide-<?php echo $w_id; ?> h2">
				    		<h2><?php echo $title; ?></h2>
			    		</div>
			    		<?php } ?>

			    		

			    </div>
			</section>

			<?php

			// This is where you run the code and display the output
			//echo __( 'Hello, World!', 'img_parallax_widget_domain' );
			echo $args['after_widget'];
		}
				
		// Widget Backend 
		public function form( $instance ) {
			if ( isset( $instance[ 'image' ] ) ) {
				$image = $instance[ 'image' ];
			}
			else {
				$image = __( 'http://cdn.wonderfulengineering.com/wp-content/uploads/2014/06/galaxy-wallpapers-9.jpg', 'img_parallax_widget_domain' );
			}
			$title = isset($instance['title']) ? $instance['title']: '';

			// Widget admin form
			?>
				<p>
				<label for="<?php echo $this->get_field_id( 'image' ); ?>"><?php _e( 'Image-link:' ); ?></label> 
				<input 
					class="widefat" 
					id="<?php echo $this->get_field_id( 'image' ); ?>" 
					name="<?php echo $this->get_field_name( 'image' ); ?>" 
					type="text" 
					value="<?php echo esc_attr( $image ); ?>" 
				/>
				</p>
				<p>
				<label for="<?php echo $this->get_field_id( 'title' ); ?>"><?php _e( 'Title:' ); ?></label> 
				<input 
					class="widefat" 
					id="<?php echo $this->get_field_id( 'title' ); ?>" 
					name="<?php echo $this->get_field_name( 'title' ); ?>" 
					type="text" 
					value="<?php echo esc_attr( $title ); ?>" 
				/>
				</p>
			<?php 
		}
			
		// Updating widget replacing old instances with new
		public function update( $new_instance, $old_instance ) {
			$instance = array();
			$instance['image'] = ( ! empty( $new_instance['image'] ) ) ? strip_tags( $new_instance['image'] ) : '';
			$instance['title'] = ( ! empty( $new_instance['title'] ) ) ? strip_tags( $new_instance['title'] ) : '';
			return $instance;
		}
	} // Class wpb_widget ends here

	// Register and load the widget
	function img_parallax_load_widget() {
		register_widget( 'img_parallax_widget' );
	}
	add_action( 'widgets_init', 'img_parallax_load_widget' );
?>