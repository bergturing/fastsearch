/**
 * grid对象
 */
var FSGrid = (function(){

    /**
     * 内部对象: 配置对象
     */
    var _config;

    /**
     * 内部对象: grid对象
     * @private
     */
    var _fsGrid = function(){};

    /**
     * 接口方法: 初始化方法
     * @param config        配置对象
     *          {
     *              selector: "",
     *              model: [
     *                  {
     *                      field: "",
     *                      width: "",
     *                      title: ""
     *                  }
     *              ]
     *
     *          }
     * @returns {FSGrid}
     */
    _fsGrid.prototype.init = function(config){
        //设置配置对象
        _config = config;

        return this;
    };

    return _fsGrid;
})();