webpackJsonp([1],{0:function(e,t){},"46AY":function(e,t){},"8aYd":function(e,t){},CNBC:function(e,t){},FBEZ:function(e,t){},Gsgj:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("7+uW"),r={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]},l=a("VU/8")({name:"App"},r,!1,null,null,null).exports,o=a("/ocq"),s=a("Xxa5"),i=a.n(s),c=a("exGp"),u=a.n(c),d=a("ifoU"),b=a.n(d),p=a("E5Az"),m=a("//Fk"),f=a.n(m),v=a("Zrlr"),h=a.n(v),g=a("wxAW"),C=a.n(g),k=a("zL8q"),w=a.n(k),T=(a("mvHQ"),a("Av7u")),_=a.n(T);_.a.enc.Utf8.parse("1234567890123456"),_.a.enc.Utf8.parse("1234567890123456");var x=a("mtWM"),y=a.n(x),D=new(function(){function e(){var t=this;h()(this,e),this.axiosService=y.a.create({baseURL:"http://localhost:8083"}),this.addInterceptor(),this.axiosService.getRequest=function(e){return t.axiosService.get(e)}}return C()(e,[{key:"addInterceptor",value:function(){var e=null;this.axiosService.interceptors.request.use(function(t){e=n.default.prototype.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});var a=localStorage.getItem("token");return a&&(t.headers.authorization=a),t},function(e){return f.a.reject(e)}),this.axiosService.interceptors.response.use(function(t){return null!=e&&e.close(),t.data.success||void 0===t.data.success?t.data.data:(console.log(t),k.Message.error({message:t.data.message}),333===t.data.code&&((a="token")&&window.localStorage.removeItem(a),z.push({path:"/"})),334===t.data.code&&f.a.resolve(t),f.a.reject(t.data.message));var a},function(e){return k.Message.error({message:e}),f.a.reject(e)})}}]),e}()),F=D.axiosService,S=function(e){return F.post("/database/run-sql",e)},q={components:{codemirror:p.codemirror},props:["conversation"],created:function(){},data:function(){return{tableDetailDiaglog:!1,tableDetail:null,showLeft:!0,props:{label:"name",children:"tables",isLeaf:"leaf"},cmOptions:{tabSize:30,mode:"text/x-sql   ",theme:"idea",lineNumbers:!0,line:!0,viewportMargin:1/0,highlightDifferences:!0,autofocus:!1,indentUnit:2,smartIndent:!0,readOnly:!1,showCursorWhenSelecting:!0,firstLineNumber:1,matchBrackets:!0,lineMapping:!0,styleActiveLine:!0,extraKeys:{Ctrl:"autocomplete"},hintOptions:{completeSingle:!1,tables:{users:["name","score","birthDate"],countries:["name","population","size"],score:["zooao"]}}},codeSnippets:"",editableTabsValue:"",editableTabs:[],tabIndex:0,connections:[],curClickConnection:{},curDbs:[],curTables:[],sqlContent:"",edittableTabsMap:new b.a,edittableTabsNameMap:new b.a,curClickTab:null,addConnDialog:!1,editConnDialog:!1,supportTypes:["MYSQL","ORACLE"],ruleForm:{name:"",dbType:"",host:"",loginUser:"",password:""},editConnForm:{},rules:{name:[{required:!0,message:"请输入连接名称",trigger:"blur"},{min:3,max:10,message:"长度在 3 到 10 个字符",trigger:"blur"}],dbType:[{required:!0,message:"请选择连接类型",trigger:"change"}],host:[{required:!0,message:"请填写主机地址",trigger:"blur"}],loginUser:[{required:!0,message:"请填写用户名",trigger:"blur"}],password:[{required:!0,message:"请填写密码",trigger:"blur"}]}}},destroyed:function(){window.onbeforeunload=null},mounted:function(){this.listTypes(),window.onbeforeunload=function(e){return(e=e||window.event)&&(e.returnValue="关闭提示"),"关闭提示"}},methods:{handleTableCommand:function(e,t){var a=this;return u()(i.a.mark(function n(){var r;return i.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if("desc"!==e){n.next=5;break}return n.next=3,l=t,F.get("/database/detail-table/"+l.connId+"/"+l.database+"/"+l.table);case 3:(r=n.sent).columnList&&r.columnList.length>0?(a.tableDetailDiaglog=!0,a.tableDetail=r):a.$message({message:"表结构为空",type:"error"});case 5:case"end":return n.stop()}var l},n,a)}))()},handleCommand:function(e,t){var a=this;"edit"===e?(this.editConnForm=t,this.editConnDialog=!0):"delete"===e&&this.$confirm("确认删除吗？").then(function(e){a.deletConn(t.id)}).catch(function(e){})},celldblclick:function(e,t){var a=function(t){t.clipboardData.setData("text/plain",e.label),t.preventDefault()};document.addEventListener("copy",a),document.execCommand("copy"),document.removeEventListener("copy",a),this.$message({message:"复制成功",type:"success"})},listTypes:function(){var e=this;return u()(i.a.mark(function t(){return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,F.get("/database/list-types");case 2:e.supportTypes=t.sent;case 3:case"end":return t.stop()}},t,e)}))()},handleSizeChange:function(e){this.curClickTab.pageSize=e,this.handleCurrentChange(1)},handleCurrentChange:function(e){if(0!=this.curClickTab.success)if(this.curClickTab.currentPage=e,this.curClickTab&&this.curClickTab.tableData)if(this.curClickTab.tableData.length<1)this.curClickTab.showTableData=[];else{var t=(e-1)*this.curClickTab.pageSize;this.curClickTab.showTableData=this.curClickTab.tableData.slice(t,t+this.curClickTab.pageSize)}else this.curClickTab.showTableData=[];else this.curClickTab.showTableData=[]},dbClick:function(e){var t=this;return u()(i.a.mark(function a(){var n;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,r=e,F.get("/database/list-tables/"+r.connId+"/"+r.database);case 2:n=a.sent,t.curTables=n;case 4:case"end":return a.stop()}var r},a,t)}))()},tableClick:function(e){var t=this;return u()(i.a.mark(function a(){var n,r,l,o;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:n="select * from "+e.table+" limit 100",r=++t.tabIndex+"",l=e.connId+"-"+e.database+"-"+e.table,null==(o=t.edittableTabsMap.get(l))?(o={title:e.database+"-"+e.table,name:r,sqlContent:n,database:e.database,connId:e.connId,tableData:[],tableMeta:[],pageSize:10,currentPage:1,showTableData:[],success:!1,errorMsg:"查询中"},t.editableTabs.push(o),t.edittableTabsMap.set(l,o),t.edittableTabsNameMap.set(r,o),t.editableTabsValue=r):(o.sqlContent=n,t.editableTabsValue=o.name),t.curClickTab=o,t.doQuery(n,o);case 7:case"end":return a.stop()}},a,t)}))()},handleOpen:function(e){1==e&&this.queryConns()},executeSql:function(e,t,a){var n=this;return u()(i.a.mark(function r(){var l,o;return i.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return l={sql:e,connId:t,database:a},n.next=3,S(l);case 3:return o=n.sent,n.abrupt("return",o);case 5:case"end":return n.stop()}},r,n)}))()},listDbs:function(e){var t=this;return u()(i.a.mark(function a(){var n;return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,a=e,F.get("/database/list-dbs/"+a);case 2:return n=t.sent,t.abrupt("return",n);case 4:case"end":return t.stop()}var a},a,t)}))()},queryConns:function(){var e=this;return u()(i.a.mark(function t(){var a;return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,n=void 0,F.get("/database/list-conns",n);case 2:a=t.sent,e.connections=a;case 4:case"end":return t.stop()}var n},t,e)}))()},deletConn:function(e){var t=this;return u()(i.a.mark(function a(){return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,n=e,F.delete("/database/delete-conn/"+n);case 2:a.sent,t.queryConns();case 4:case"end":return a.stop()}var n},a,t)}))()},editUpdateConn:function(e){var t=this;return u()(i.a.mark(function a(){return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,n=e,F.post("/database/update-conn",n);case 2:a.sent,t.queryConns(),t.editConnDialog=!1;case 5:case"end":return a.stop()}var n},a,t)}))()},saveConn:function(e){var t=this;return u()(i.a.mark(function a(){return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,n=e,F.post("/database/save-conn",n);case 2:a.sent,t.queryConns(),t.addConnDialog=!1;case 5:case"end":return a.stop()}var n},a,t)}))()},refreshDb:function(e){var t=this;return u()(i.a.mark(function a(){var n;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,t.listDbs(e);case 2:n=a.sent,t.curDbs=n,t.curTables=[];case 5:case"end":return a.stop()}},a,t)}))()},removeTab:function(e){var t=this,a=this.editableTabs,n=this.editableTabsValue;n===e&&a.forEach(function(r,l){if(r.name===e){var o=a[l+1]||a[l-1];o&&(n=o.name,t.curClickTab=o)}}),this.editableTabsValue=n;var r=null;this.editableTabs=a.filter(function(t){var a=t.name!==e;return a||(r=t.connId+"-"+t.database),a}),null!==r&&this.edittableTabsMap.delete(r),this.edittableTabsNameMap.delete(e)},clickTab:function(e){var t=this.edittableTabsNameMap.get(e.name);null!==t&&(this.curClickTab=t)},runSql:function(){this.doQuery(this.curClickTab.sqlContent,this.curClickTab)},checkRunSql:function(){this.doQuery(window.getSelection().toString(),this.curClickTab)},doQuery:function(e,t){var a=this;return u()(i.a.mark(function n(){var r;return i.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,a.executeSql(e,t.connId,t.database);case 2:r=n.sent,t.tableMeta=r.tableMeta,t.tableData=r.tableData,t.success=r.success,t.errorMsg=r.errorMsg,t.sqlContent=r.executeSql,a.handleCurrentChange(1);case 9:case"end":return n.stop()}},n,a)}))()},submitForm:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.saveConn(t.ruleForm)})},resetForm:function(e){this.$refs[e].resetFields()},submitUpdateForm:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.editUpdateConn(t.editConnForm)})}}},M={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"app"}},[a("div",[a("el-header",{staticStyle:{"text-align":"left","font-size":"12px","background-color":"white"}},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addConnDialog=!0}}},[e._v("\n                新建查询\n            ")])],1),e._v(" "),a("div",[a("el-container",{staticStyle:{border:"1px solid #eee"}},[e.showLeft?a("el-aside",{staticStyle:{"background-color":"rgb(238, 241, 246)"},attrs:{width:"10%"}},[a("el-menu",{staticStyle:{"background-color":"rgb(238, 241, 246)"},on:{open:e.handleOpen}},[a("el-submenu",{attrs:{index:"1"}},[a("template",{slot:"title"},[a("span",[e._v("我的连接")])]),e._v(" "),e._l(e.connections,function(t){return a("div",{key:t.id},[a("el-dropdown",{staticStyle:{width:"100%"},attrs:{placement:"bottom"},on:{command:function(a){return e.handleCommand(a,t)}}},[a("el-menu-item",{attrs:{index:t.name},on:{click:function(a){return e.refreshDb(t.id)}}},[e._v("\n                                        "+e._s(t.name)+"\n                                    ")]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",{attrs:{command:"edit"}},[e._v("编辑")]),e._v(" "),a("el-dropdown-item",{attrs:{command:"delete"}},[e._v("删除")])],1)],1)],1)})],2)],1)],1):e._e(),e._v(" "),e.showLeft?a("el-aside",{attrs:{width:"10%"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.curDbs,border:""},on:{"cell-click":e.dbClick}},[a("el-table-column",{attrs:{prop:"database",label:"库"}})],1)],1):e._e(),e._v(" "),e.showLeft?a("el-aside",{attrs:{width:"15%"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.curTables,border:""},on:{"cell-click":e.tableClick}},[a("el-table-column",{attrs:{label:"表"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-dropdown",{staticStyle:{width:"100%"},attrs:{placement:"bottom-start"},on:{command:function(a){return e.handleTableCommand(a,t.row)}}},[a("span",{staticStyle:{width:"100%"}},[e._v(e._s(t.row.table))]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",{attrs:{command:"desc"}},[e._v("查看结构")])],1)],1)]}}],null,!1,145013981)})],1)],1):e._e(),e._v(" "),e.editableTabs.length>0?a("el-container",[a("el-header",{staticStyle:{"text-align":"left","font-size":"12px"}},[a("el-button",{on:{click:e.runSql}},[e._v("运行")]),e._v(" "),a("el-button",{on:{click:e.checkRunSql}},[e._v("选中运行")]),e._v(" "),e.showLeft?a("el-button",{on:{click:function(t){e.showLeft=!1}}},[e._v("收起侧边")]):e._e(),e._v(" "),e.showLeft?e._e():a("el-button",{on:{click:function(t){e.showLeft=!0}}},[e._v("打开侧边")])],1),e._v(" "),a("el-main",[a("el-tabs",{attrs:{type:"card",closable:""},on:{"tab-remove":e.removeTab,"tab-click":e.clickTab},model:{value:e.editableTabsValue,callback:function(t){e.editableTabsValue=t},expression:"editableTabsValue"}},[e._l(e.editableTabs,function(e,t){return a("el-tab-pane",{key:e.name,attrs:{label:e.title,name:e.name}})}),e._v(" "),null!=e.curClickTab?a("codemirror",{attrs:{options:e.cmOptions},model:{value:e.curClickTab.sqlContent,callback:function(t){e.$set(e.curClickTab,"sqlContent",t)},expression:"curClickTab.sqlContent"}}):e._e()],2),e._v(" "),null!=e.curClickTab&&1==e.curClickTab.success&&null!=e.curClickTab.showTableData&&e.curClickTab.tableMeta&&e.curClickTab.tableMeta.length>0?a("div",[null!=e.curClickTab&&1==e.curClickTab.success&&null!=e.curClickTab.showTableData&&e.curClickTab.tableMeta&&e.curClickTab.tableMeta.length>0?a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.curClickTab.showTableData},on:{"header-click":e.celldblclick}},e._l(e.curClickTab.tableMeta,function(e){return a("el-table-column",{key:e.label,attrs:{prop:e.label,label:e.label,width:"180"}})}),1):e._e(),e._v(" "),null!=e.curClickTab?a("el-pagination",{attrs:{"current-page":e.curClickTab.currentPage,"page-sizes":[10,20,100,200,300,400],"page-size":e.curClickTab.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.curClickTab.tableData.length},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}):e._e()],1):a("div",[e.curClickTab&&0==e.curClickTab.success?a("span",[e._v(e._s(e.curClickTab.errorMsg))]):a("span",[e._v("查询中……")])])],1)],1):e._e()],1)],1)],1),e._v(" "),a("div",[a("el-dialog",{attrs:{title:"添加连接",visible:e.addConnDialog},on:{"update:visible":function(t){e.addConnDialog=t}}},[a("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"连接名称",prop:"name"}},[a("el-input",{model:{value:e.ruleForm.name,callback:function(t){e.$set(e.ruleForm,"name",t)},expression:"ruleForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"连接类型",prop:"dbType"}},[a("el-select",{attrs:{placeholder:"请选择连接类型"},model:{value:e.ruleForm.dbType,callback:function(t){e.$set(e.ruleForm,"dbType",t)},expression:"ruleForm.dbType"}},e._l(e.supportTypes,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"主机",prop:"host"}},[a("el-input",{model:{value:e.ruleForm.host,callback:function(t){e.$set(e.ruleForm,"host",t)},expression:"ruleForm.host"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"用户",prop:"loginUser"}},[a("el-input",{model:{value:e.ruleForm.loginUser,callback:function(t){e.$set(e.ruleForm,"loginUser",t)},expression:"ruleForm.loginUser"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"密码",prop:"password"}},[a("el-input",{attrs:{type:"password"},model:{value:e.ruleForm.password,callback:function(t){e.$set(e.ruleForm,"password",t)},expression:"ruleForm.password"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("立即创建")]),e._v(" "),a("el-button",{on:{click:function(t){return e.resetForm("ruleForm")}}},[e._v("重置")])],1)],1)],1)],1),e._v(" "),a("div",[a("el-dialog",{attrs:{title:"编辑连接",visible:e.editConnDialog},on:{"update:visible":function(t){e.editConnDialog=t}}},[a("el-form",{ref:"editConnForm",staticClass:"demo-ruleForm",attrs:{model:e.editConnForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"连接名称",prop:"name"}},[a("el-input",{model:{value:e.editConnForm.name,callback:function(t){e.$set(e.editConnForm,"name",t)},expression:"editConnForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"连接类型",prop:"dbType"}},[a("el-select",{attrs:{placeholder:"请选择连接类型"},model:{value:e.editConnForm.dbType,callback:function(t){e.$set(e.editConnForm,"dbType",t)},expression:"editConnForm.dbType"}},e._l(e.supportTypes,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"主机",prop:"host"}},[a("el-input",{model:{value:e.editConnForm.host,callback:function(t){e.$set(e.editConnForm,"host",t)},expression:"editConnForm.host"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"用户",prop:"loginUser"}},[a("el-input",{model:{value:e.editConnForm.loginUser,callback:function(t){e.$set(e.editConnForm,"loginUser",t)},expression:"editConnForm.loginUser"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"密码",prop:"password"}},[a("el-input",{attrs:{type:"password"},model:{value:e.editConnForm.password,callback:function(t){e.$set(e.editConnForm,"password",t)},expression:"editConnForm.password"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitUpdateForm("editConnForm")}}},[e._v("更新")])],1)],1)],1)],1),e._v(" "),a("div",[a("el-dialog",{attrs:{title:"表结构",visible:e.tableDetailDiaglog},on:{"update:visible":function(t){e.tableDetailDiaglog=t}}},[e.tableDetail&&e.tableDetail.columnList?a("div",[a("h3",[e._v("字段结构")]),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableDetail.columnList}},[a("el-table-column",{attrs:{prop:"name",label:"名称"}}),e._v(" "),a("el-table-column",{attrs:{prop:"type",label:"类型"}}),e._v(" "),a("el-table-column",{attrs:{prop:"autoIncrement",label:"自增"}}),e._v(" "),a("el-table-column",{attrs:{prop:"size",label:"长度"}}),e._v(" "),a("el-table-column",{attrs:{prop:"digits",label:"精度"}}),e._v(" "),a("el-table-column",{attrs:{prop:"nullable",label:"空值"}}),e._v(" "),a("el-table-column",{attrs:{prop:"defaultValue",label:"默认值"}}),e._v(" "),a("el-table-column",{attrs:{prop:"remarks",label:"备注"}})],1)],1):e._e(),e._v(" "),e.tableDetail&&e.tableDetail.indexList?a("div",[a("h3",[e._v("索引")]),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableDetail.indexList}},[a("el-table-column",{attrs:{prop:"name",label:"名称"}}),e._v(" "),a("el-table-column",{attrs:{prop:"fields",label:"字段列表"}}),e._v(" "),a("el-table-column",{attrs:{prop:"unique",label:"唯一"}})],1)],1):e._e()])],1)])},staticRenderFns:[]};var L=a("VU/8")(q,M,!1,function(e){a("fKvh")},"data-v-2e873979",null).exports;n.default.use(o.a);var z=new o.a({routes:[{path:"/",name:"database",component:L,meta:{requiredAuth:!1,allowBack:!1}}]});a("tvR6"),a("8aYd"),a("46AY"),a("CNBC"),a("OzWN"),a("FBEZ"),a("wYHC"),a("VHDu"),a("W1jn"),a("D1j6"),a("vupN"),a("IM2z"),a("E2cS"),a("l5LD"),a("Buz9"),a("Ou4A"),a("Gsgj"),a("unjE"),a("QggT"),a("nxoM"),a("lcKf");window.jsonlint=a("D1j6"),n.default.use(p.codemirror),n.default.prototype.$axios=D.axiosService,n.default.prototype.$loading=k.Loading,n.default.use(w.a),new n.default({el:"#app",render:function(e){return e(l)},router:z,components:{App:l},template:"<App/>"})},OzWN:function(e,t){},fKvh:function(e,t){},tvR6:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.c4b0b0e7ecc1b3388d8f.js.map