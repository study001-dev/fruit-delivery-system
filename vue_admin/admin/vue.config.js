// vue.config.js
const path = require('path')
const webpack = require('webpack') // 引入webpack（已取消注释）

function resolve(dir) {
    return path.join(__dirname, dir)
}

function publicPath() {
    if (process.env.NODE_ENV == 'production') {
        return "././";
    } else {
        return "/";
    }
}

module.exports = {
    publicPath: publicPath(),
    
    // ========= 新增 Sass 全局变量配置 =========
    css: {
        loaderOptions: {
            scss: {
                // 这里添加您的全局变量文件路径
                additionalData: `
                    @import "@/assets/styles/variables.scss"; 
                    @import "@/assets/styles/mixins.scss";
                `,
                implementation: require('sass') // 使用 dart-sass 编译器
            }
        }
    },
    // ========= 新增配置结束 =========
    
    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src')
            }
        },
        // 可选的插件配置（示例）
        plugins: [
            // 环境变量定义插件
            new webpack.DefinePlugin({
                'process.env.APP_VERSION': JSON.stringify(require('./package.json').version)
            })
        ],
        performance: {
            hints: process.env.NODE_ENV === 'production' ? 'warning' : false,
            maxAssetSize: 500000, // 资源大小限制（字节）
            maxEntrypointSize: 500000
        }
    },
    
    lintOnSave: false,
    
    devServer: {
        host: "0.0.0.0",
        port: 8081,
        hot: true,
        https: false,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080/springboot',
                changeOrigin: true,
                pathRewrite: { '^/api': '' }
            }
        }
    },
    
    chainWebpack(config) {
        config.module
            .rule('svg')
            .exclude.add(resolve('src/icons'))
            .end()
        
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()
        
        // ========= 新增性能优化配置 =========
        // 生产环境配置
        if (process.env.NODE_ENV === 'production') {
            // 关闭 source map
            config.devtool('none')
            
            // 代码分割优化
            config.optimization.splitChunks({
                chunks: 'all',
                maxInitialRequests: Infinity,
                minSize: 20000,
                cacheGroups: {
                    vendor: {
                        test: /[\\/]node_modules[\\/]/,
                        name(module) {
                            const packageName = module.context.match(/[\\/]node_modules[\\/](.*?)([\\/]|$)/)[1];
                            return `npm.${packageName.replace('@', '')}`;
                        }
                    }
                }
            })
        }
    },
    
    // ========= 新增生产环境优化配置 =========
    productionSourceMap: false // 关闭生产环境的 source map
}

