jQuery.adminApprove = {
		approveDataTable:null,
		toSave:false,
		initSearchDataTable : function() {
			if (this.approveDataTable == null) {
				this.approveDataTable = $('#dt_table_view').dataTable({
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
					"sAjaxSource" : $.ace.getContextPath() + "/admin/approve/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var project = $("#project").val();
						if (!!project) {
							aoData.push({
								"name" : "project.id",
								"value" : project
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
					},{
						"mDataProp" : "project.name"
					},{
						"mDataProp" : "user.userName"
					}, {
						"mDataProp" : "createDate"
					}, {
						"mDataProp" : "remark"
					}, {
						"mDataProp" : "state"
					}, {
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
					{
						'aTargets' : [5],
						'fnRender' : function(oObj, sVal) {
							return "<span class=\"label label-success\">"+sVal+"</span>";
						}
					},
						{
							'aTargets' : [6],
							'fnRender' : function(oObj, sVal) {
								return "<button class=\"btn2 btn-info\" onclick=\"$.adminApprove.pass("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>通过</button>"+
								 "  <button class=\"btn2 btn-info\" onclick=\"$.adminApprove.notpass("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 退回</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.approveDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.approveDataTable.fnDraw(oSettings);
			}

		},
		pass :function(id){
			bootbox.confirm( "是否确认审核通过？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/approve/pass?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminApprove.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		notpass :function(id){
			bootbox.confirm( "是否确认退回？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/approve/notpass?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminApprove.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
};