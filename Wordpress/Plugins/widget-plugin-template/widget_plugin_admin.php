<?php 
    // Check if this is a reload after the Submit-button was pressed or not.
    if($_POST['widget_plugin_hidden'] == 'Y') {
        // Form data sent
        // Get the data and update it in update_option
        $s1 = $_POST['setting_1'];
        update_option('setting_1', $s1);
         
        $s2 = $_POST['setting_2'];
        update_option('setting_2', $s2);
         
        $sA = $_POST['setting_a'];
        update_option('setting_a', $sA);
         
        $sB = $_POST['setting_b'];
        update_option('my_dbpwd', $setting_b);
        ?>
        <!-- Display a notification that the settings have been saved. -->
        <div class="updated"><p><strong><?php _e('Options saved.' ); ?></strong></p></div>
        <?php
    } else {
        // Normal page display 
        $s1 = get_option('setting_1');
        $s2 = get_option('setting_2');
        $sA = get_option('setting_A');
        $sB = get_option('setting_B');
    }
?>
<div class="wrap">
    <?php echo "<h2>" . __( 'Widget plugin settings Options', 'my-widget-plugin' ) . "</h2>"; ?>
    <form name="widget_plugin_form" method="post" action="<?php echo str_replace( '%7E', '~', $_SERVER['REQUEST_URI']); ?>">
        <input type="hidden" name="widget_plugin_hidden" value="Y">
        <?php    echo "<h4>" . __( 'Some settings', 'my-widget-plugin' ) . "</h4>"; ?>
        <p><?php _e("Setting 1: " ); ?><input type="text" name="setting_1" value="<?php echo $s1; ?>" size="20"><?php _e(" ex: Uno" ); ?></p>
        <p><?php _e("Setting 2: " ); ?><input type="text" name="setting_2" value="<?php echo $s2; ?>" size="20"><?php _e(" ex: Dos" ); ?></p>
        <hr />
        <?php    echo "<h4>" . __( 'Some other settings', 'my-widget-plugin' ) . "</h4>"; ?>
        <p><?php _e("Setting A: " ); ?><input type="text" name="setting_a" value="<?php echo $sA; ?>" size="20"><?php _e(" ex: Ape" ); ?></p>
        <p><?php _e("Setting B: " ); ?><input type="text" name="setting_b" value="<?php echo $sB; ?>" size="20"><?php _e(" ex: Banana" ); ?></p>
        <p class="submit">
            <input type="submit" name="Submit" value="<?php _e('Update Options', 'my-widget-plugin' ) ?>" />
        </p>
    </form>
</div>