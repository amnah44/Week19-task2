package com.examble.task1.ui.test

data class Post(
    val ID: Int,
    val ImageClass: String,
    val PostClass: String,
    val TermClass: String,
    val _edit_last: String,
    val _edit_lock: String,
    val _encloseme: String,
    val _pingme: String,
    val _thumbnail_id: String,
    val _use_editor_header: String,
    val _use_template_header: String,
    val category: List<String>,
    val comment_count: String,
    val comment_status: String,
    val custom: Custom,
    val filter: String,
    val guid: String,
    val id: Int,
    val img_url: String,
    val menu_order: Int,
    val object_type: String,
    val ping_status: String,
    val pinged: String,
    val post_author: String,
    val post_content: String,
    val post_content_filtered: String,
    val post_date: String,
    val post_date_gmt: String,
    val post_excerpt: String,
    val post_header_image_thumbnail_id: String,
    val post_mime_type: String,
    val post_modified: String,
    val post_modified_gmt: String,
    val post_name: String,
    val post_parent: Int,
    val post_password: String,
    val post_status: String,
    val post_title: String,
    val post_type: String,
    val post_views_counter: String,
    val send_fcm_checkbox: String,
    val slug: String,
    val status: String,
    val to_ping: String,
    val url: String,
    val use_editor_header: String,
    val use_template_header: String
)