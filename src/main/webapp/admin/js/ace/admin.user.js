jQuery.adminUser = {
		userDataTable:null,
		initSearchDataTable : function() {
			if (this.userDataTable == null) {
				this.userDataTable = $('#dt_user_view').dataTable({
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
					"sAjaxSource" : $.ace.getContextPath() + "/admin/user/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var userName = $("#_userName").val();
						if (!!userName) {
							aoData.push({
								"name" : "userName",
								"value" : userName
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
						"mDataProp" : "userName"
					}, {
						"mDataProp" : "password"
					}, {
						"mDataProp" : "school"
					}, {
						"mDataProp" : "className"
					}, {
						"mDataProp" : "sex"
					}, {
						"mDataProp" : "birthDay"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [7],
							'fnRender' : function(oObj, sVal) {
								return "<button class=\"btn2 btn-info\" onclick=\"$.adminUser.showEdit("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>修改</button>"+
								 "  <button class=\"btn2 btn-info\" onclick=\"$.adminUser.deleteUser("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.userDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.userDataTable.fnDraw(oSettings);
			}

		},
		deleteUser :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/user/delete?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminUser.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showUserAddModal: function(id){
			$("#userid").val(id);
			$('#user_edit_modal').modal({
			});
			$("#user_modal_header_label").text("新增用户信息");
			$("#user_edit_modal").modal('show');
		},
		showEdit: function (id){
			$("#userid").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/user/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#userName").val(json.resultMap.user.userName);
    					$("#school").val(json.resultMap.user.school);
    					$("#className").val(json.resultMap.user.className);
    					$("#password").val(json.resultMap.user.password);
    					$("#sex").val(json.resultMap.user.sex);
    					$("#birthDay").val(json.resultMap.user.birthDay);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#user_edit_modal").modal('show');
		},
		
		saveUser: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/user/update",
    			data:{
    				"user.id":$("#userid").val(),
    				"user.userName":$("#userName").val(),
    				"user.school":$("#school").val(),
    				"user.className":$("#className").val(),
    				"user.sex":$("#sex").val(),
    				"user.password":$("#password").val(),
    				"user.birthDay":$("#birthDay").val()
    			},
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#user_edit_modal").modal('hide');
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.adminUser.initSearchDataTable();
    				
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		}
};