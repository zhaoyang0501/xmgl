jQuery.adminlog = {
		logDataTable:null,
		toSave:false,
		initSearchDataTable : function() {
			if (this.logDataTable == null) {
				this.logDataTable = $('#dt_log_view').dataTable({
					"sDom" : "<'row-fluid'<'span6'l>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sSearch" : "",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
					"bAutoWidth" : false,
					"iDisplayLength" : 10,
					"aLengthMenu" : [ 5, 10, 25, 50],
					"bServerSide" : true,
					"sServerMethod" : "POST",
					"bProcessing" : true,
					"bSort" : false,
					"sAjaxSource" : $.ace.getContextPath() + "/admin/log/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var start = $("#start").val();
						var end = $("#end").val();
						if (!!start) {
							aoData.push({
								"name" : "start",
								"value" : start
							});
						}
						if (!!end) {
							aoData.push({
								"name" : "end",
								"value" : end
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data.resultMap);
							}
						});
					},
					"aoColumns" : [ {
						"mDataProp" : "id"
					}, {
						"mDataProp" : "level"
					}, {
						"mDataProp" : "logDate"
					}, {
						"mDataProp" : "user.userName"
					}, {
						"mDataProp" : "ip"
					}, {
						"mDataProp" : "remark"
					}],
					"aoColumnDefs" : [
                  {
						'aTargets' : [1 ],
						'fnRender' : function(oObj, sVal) {
							if(sVal==0){
								return '<span class="label label-info">正常</span>';
							}else if(sVal==1){
								return '<span class="label label-warning">警告</span>';
							}else{
								return '<span class="label label-important">危险</span>';
							}
							
						}
					},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.logDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.logDataTable.fnDraw(oSettings);
			}

		}
};