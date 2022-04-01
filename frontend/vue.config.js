const CompressionPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = /\.(js|css|json|txt|ico|svg)(\?.*)?$/i
const TerserPlugin = require('terser-webpack-plugin')
module.exports = {
  configureWebpack: config => {
    config.optimization = {
      minimize: process.env.NODE_ENV === 'production',
      minimizer: [
        new TerserPlugin({
          test: /\.js(\?.*)?$/i, // 匹配参与压缩的文件
          parallel: true, // 使用多进程并发运行
          terserOptions: {
            // Terser 压缩配置
            output: { comments: false }
          },
          extractComments: false // 将注释剥离到单独的文件中
        })
      ]
    }
  },
  chainWebpack(config) {
    if (process.env.NODE_ENV === 'production') {
      // 压缩
      config.plugin('compressionPlugin').use(
          new CompressionPlugin({
            filename: '[path].gz[query]',
            algorithm: 'gzip',
            test: productionGzipExtensions,
            threshold: 10240,
            minRatio: 0.8
            // deleteOriginalAssets: true
          })
      )
    }
  },
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
};
