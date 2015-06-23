<?php 
    /*
    Plugin Name: My Widget Plugin
    Plugin URI: http://www.shapeapp.se/myplugin
    Description: A template for widget-plugin where i add common plugin modules. Not made for functioning as a real plugin.
    Author: Shape App
    Version: 1.0
    Text Domain: my-widget-plugin
    Author URI: http://www.shapeapp.se/
    */


    // Content:
    // ========
    // 1.0 Enqueue scripts
    // 2.0 Create Widget
    // 3.0 Register Widget



    //============================================================
    // 1.0 Enqueue scripts
    //============================================================

    //Define a function for enqueueing scripts
    function my_plugin_register_enqueue() {
	    if ( ! is_admin() ) { // To not enqueue extra scripts while in admin-mode

	        wp_enqueue_script(
	        	'MY_REQIURED_HANDLE', 
	        	plugin_dir_url( __FILE__ ) . 'myjavascript.js',  //Gets path relative to plugin_url
	        	array( 'jquery' ),  //Dependencies
	        	'VERSION', // Can be null... 
	        	TRUE // 'In footer'
	        );  
	    }
	}
	//Add the function to the enqueue-scripts hook.
	add_action( 'wp_enqueue_scripts', 'my_plugin_register' );


    //============================================================
    // 2.0 Create Widget
    //============================================================
    // Questions about th __('String', 'domain') are answered here:
    // https://developer.wordpress.org/plugins/internationalization/how-to-internationalize-your-plugin/

	class my_plugin_name extends WP_Widget {

		function __construct() {
			parent::__construct(
				// Base ID of your widget
				'my_plugin_name', 

				// Widget name will appear in UI
				__('My Widget Plugin', 'my-widget-plugin'), 

				// Widget description
				array( 'description' => __( 'Image widget with parallax effect', 'my-widget-plugin' ), ) 
			);
		}

		// Creating widget front-end
		// This is where the action happens
		public function widget( $args, $instance ) {

			//Load saved data from backend.
			$title = isset($instance['title']) ? $instance['title']: '';

			// before and after widget arguments are defined by themes
			echo $args['before_widget'];

			//Get the id of this intansce of the widget.
			$w_id = $args['widget_id'];


			// This is where you run the code and display the output
			//echo __( 'Hello, World!', 'my-widget-plugin' );
		?>
			<h1>My widget plugin</h1>
			<p>Here goes all HTML you want to display in your widget.</p>
		<?php
			echo $args['after_widget'];
		}
				
		// Widget Backend 
		public function form( $instance ) {
			$title = isset($instance['title']) ? $instance['title']: '';

			// Widget admin form
			?>
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
			$instance['title'] = ( ! empty( $new_instance['title'] ) ) ? strip_tags( $new_instance['title'] ) : '';
			return $instance;
		}
	} // Class wpb_widget ends here


    //============================================================
    // 3.0 Register Widget
    //============================================================

	// Register and load the widget
	function img_parallax_load_widget() {
		register_widget( 'img_parallax_widget' );
	}
	add_action( 'widgets_init', 'img_parallax_load_widget' );
?>