/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{

	//config.filebrowserImageBrowseUrl =  'ckfinder/ckfinder.html?Type=Images'; 
	//config.filebrowserFlashBrowseUrl=  'ckfinder/ckfinder.html?Type=Flash'; 
	//config.filebrowserUploadUrl= 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';  
	//config.filebrowserImageUploadUrl=  'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';  
   // config.filebrowserFlashUploadUrl=  'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.filebrowserBrowseUrl = '/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
    config.width = '800px'; // 宽度
    config.height = '300px'; // 高度
    //config.image_previewText = ' ';
	
	config.toolbar_Full = [
	['Font','FontSize','Table','HorizontalRule'],
	['NumberedList','BulletedList','Outdent','Indent'],
	['Anchor','PageBreak','SpecialChar','SelectAll','Paste','PasteText','PasteFromWord'],
	['Blockquote','ShowBlocks','Replace'],
	'/',
	['Bold','Italic','Underline','TextColor','BGColor','Link','Unlink','RemoveFormat'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],['Image','Flash'],
	['Strike','Subscript','Superscript','Undo','Redo','Cut','Copy','Source']
	];
	config.skin = 'BootstrapCK-Skin';
	//config.enterMode=2;
	config.font_names='正文/正文;宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+config.font_names;
	config.fontSize_sizes ='10/10px;11/11px;12/12px;13/13px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px';
	config.language = "zh-cn"; 
};
