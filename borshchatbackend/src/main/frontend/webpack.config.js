const HtmlWebPackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');


module.exports = {
    entry: './chat/index.js',

    module: {
        rules: [
            {
                test: /.(js|jsx)$/,
                exclude: /node_modules/,
                resolve: {
                    extensions: ['.js', '.jsx'],
                },
                use: {
                    loader: 'babel-loader',
                },
            },
            {
                test: /.html$/,
                use: [
                    {
                        loader: 'html-loader',
                    },
                ],
            },
            { test: /\.css$/, use: [ 'style-loader', 'css-loader' ] },
            // { test: /\.svg$/, use: 'svg-inline-loader' },
            {
                test: /\.(png|jpe?g|gif|mp3|svg)$/i,
                use: [
                    {
                        loader: 'file-loader',
                    },
                ],
            },


        ],
    },

    plugins: [
        new HtmlWebPackPlugin({
            template: './chat/index.html',
            filename: './index.html',
        }),
    ],
};