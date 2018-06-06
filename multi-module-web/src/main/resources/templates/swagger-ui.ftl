<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8">
    <title>家博会前端API文档</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Source+Code+Pro:300,600|Titillium+Web:400,600,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/swagger/swagger-ui.css" >
    <link rel="icon" type="image/png" href="${basePath}/static/swagger/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="${basePath}/static/swagger/favicon-16x16.png" sizes="16x16" />
<#--      <link rel="icon" sizes="124x124" href="${basePath}/static/login/images/favicon.png">
      <link rel="shortcut icon" href="${basePath}/static/login/images/favicon.png">-->
    <style>
      html
      {
        box-sizing: border-box;
        overflow: -moz-scrollbars-vertical;
        overflow-y: scroll;
      }

      *,
      *:before,
      *:after
      {
        box-sizing: inherit;
      }

      body
      {
        margin:0;
        background: #fafafa;
      }
    </style>
  </head>

  <body>
    <div id="swagger-ui"></div>

    <script src="${basePath}/static/swagger/swagger-ui-bundle.js"> </script>
    <script src="${basePath}/static/swagger/swagger-ui-standalone-preset.js"> </script>
    <script>
    window.onload = function() {

      // Build a system
      const ui = SwaggerUIBundle({
       // url: "http://petstore.swagger.io/v2/swagger.json",
          url :"${basePath}/v2/api-docs",
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ],
        plugins: [
          SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
      })

      window.ui = ui
    }
  </script>
  </body>
</html>

