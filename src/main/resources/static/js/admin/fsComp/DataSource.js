/**
 * 数据源对象
 */
var FSDataSource = (function(){

    /**
     * 内部配置对象
     */
    var _config;

    /**
     * 请求到的数据
     */
    var _datas = [];

    /**
     * 内部对象: 数据源对象
     * @private
     */
    var _fsDataSource = function(){};

    /**
     * 接口方法: 初始化方法
     * @param config            配置对象
     *          {
     *              transport: {
     *                  create: {
     *                      url: "",
     *
     *                  },
     *                  delete: {
     *                      url: ""
     *                  },
     *                  update: {
     *                      url: ""
     *                  },
     *                  query: {
     *                      url: "",
     *                      type: "",
     *                      contentType: "",
     *                      dataType: "",
     *                      data: ""
     *                  },
     *                  batchDelete: {
     *                      url: ""
     *                  }
     *              },
     *              bootData: "",
     *              schema: {
     *                  data: "",
     *                  total: "",
     *                  model: {
     *                      id: "",
     *                      fields: [
     *                          {
     *                              name: "",
     *                              type: "",
     *                              required: ""
     *                          }
     *                      ]
     *                  }
     *              }
     *          }
     * @returns {_fsDataSource}
     */
    _fsDataSource.prototype.init = function(config){

        //设置配置对象
        _config = config;

        //在初始化的时候加载数据
        if(_config.bootData){
            this.sych();
        }

        //返回当前对象
        return this;
    };

    /**
     * 接口方法: 从远端获取数据
     */
    _fsDataSource.prototype.sych = function(){
        //设置数据为空
        _datas = [];

        //获取数据
        $.ajax({
            url: _config.transport.create.url,
            type: _config.transport.create.type,
            contentType: _config.transport.create.contentType,
            dataType: _config.transport.create.dataType || 'json',
            data: _config.transport.create.data || "",
            success: function (response) {
                if(response.code === 200){
                    $.each(response[_config.schema.data], function(index, item){
                        var _model = _config.schema.model;

                        var _data = {};

                        _data['id'] = item[_model.id];

                        $.each(_model.fields, function(index, field){
                            _data[field.name] = item[field.name];
                        });

                        //将数据加入
                        _datas.push(_data);
                    });

                }else{
                    console.log(response.msg);
                }
            },
            error: function (response) {
                console.log(response.msg);
            }
        });
    };

    /**
     * 接口方法: 获取数据
     * @returns {Array}
     */
    _fsDataSource.prototype.datas = function(){
        return _datas;
    };

    /**
     * 接口方法: 增加数据
     * @param data
     */
    _fsDataSource.prototype.add = function(data){

    };

    /**
     * 接口方法: 更新数据
     * @param id
     * @param data
     */
    _fsDataSource.prototype.update = function(id, data){

    };

    /**
     * 接口方法: 删除数据
     * @param id
     */
    _fsDataSource.prototype.delete = function(id){

    };

    /**
     * 接口方法: 批量删除数据
     * @param ids
     */
    _fsDataSource.prototype.batchDelete = function(ids){

    };


    //返回对象
    return _fsDataSource;
})();