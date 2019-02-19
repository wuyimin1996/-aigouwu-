/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn';
	config.filebrowserBrowseUrl = 'browser.jsp';
	config.filebrowserImageBrowseUrl = 'browser.jsp?type=Images';

	config.filebrowserUploadUrl = 'ckupload.jsp';
	config.filebrowserImageUploadUrl = 'ckupload.jsp?type=Images';

	config.filebrowserWindowWidth = '640';
	config.filebrowserWindowHeight = '480';
};
