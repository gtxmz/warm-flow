import{u as E,r as i,F,d as s,G as K,o as x,c as O,e as t,f as a,h as o,H as y,l as G,m,z as C,I as H,D as V,p as S,B as P,v as q}from"./index-CHQH9-TC.js";import{a as J,u as L}from"./user-Bu1ajp0e.js";const M={class:"app-container"},Q=m("h4",{class:"form-header h4"},"基本信息",-1),W=m("h4",{class:"form-header h4"},"角色信息",-1),X={style:{"text-align":"center","margin-left":"-120px","margin-top":"30px"}},Y=P({name:"AuthRole"}),oe=Object.assign(Y,{setup(Z){const b=E(),{proxy:f}=q(),g=i(!0),v=i(0),u=i(1),d=i(10),h=i([]),_=i([]),r=i({nickName:void 0,userName:void 0,userId:void 0});function $(l){f.$refs.roleRef.toggleRowSelection(l)}function B(l){h.value=l.map(e=>e.roleId)}function T(l){return l.roleId}function w(){const l={path:"/system/user"};f.$tab.closeOpenPage(l)}function D(){const l=r.value.userId,e=h.value.join(",");L({userId:l,roleIds:e}).then(c=>{f.$modal.msgSuccess("授权成功"),w()})}return(()=>{const l=b.params&&b.params.userId;l&&(g.value=!0,J(l).then(e=>{r.value=e.user,_.value=e.roles,v.value=_.value.length,F(()=>{_.value.forEach(c=>{c.flag&&f.$refs.roleRef.toggleRowSelection(c)})}),g.value=!1}))})(),(l,e)=>{const c=s("el-input"),I=s("el-form-item"),k=s("el-col"),U=s("el-row"),N=s("el-form"),p=s("el-table-column"),j=s("el-table"),A=s("pagination"),R=s("el-button"),z=K("loading");return x(),O("div",M,[Q,t(N,{model:o(r),"label-width":"80px"},{default:a(()=>[t(U,null,{default:a(()=>[t(k,{span:8,offset:2},{default:a(()=>[t(I,{label:"用户昵称",prop:"nickName"},{default:a(()=>[t(c,{modelValue:o(r).nickName,"onUpdate:modelValue":e[0]||(e[0]=n=>o(r).nickName=n),disabled:""},null,8,["modelValue"])]),_:1})]),_:1}),t(k,{span:8,offset:2},{default:a(()=>[t(I,{label:"登录账号",prop:"userName"},{default:a(()=>[t(c,{modelValue:o(r).userName,"onUpdate:modelValue":e[1]||(e[1]=n=>o(r).userName=n),disabled:""},null,8,["modelValue"])]),_:1})]),_:1})]),_:1})]),_:1},8,["model"]),W,y((x(),G(j,{"row-key":T,onRowClick:$,ref:"roleRef",onSelectionChange:B,data:o(_).slice((o(u)-1)*o(d),o(u)*o(d))},{default:a(()=>[t(p,{label:"序号",width:"55",type:"index",align:"center"},{default:a(n=>[m("span",null,C((o(u)-1)*o(d)+n.$index+1),1)]),_:1}),t(p,{type:"selection","reserve-selection":!0,width:"55"}),t(p,{label:"角色编号",align:"center",prop:"roleId"}),t(p,{label:"角色名称",align:"center",prop:"roleName"}),t(p,{label:"权限字符",align:"center",prop:"roleKey"}),t(p,{label:"创建时间",align:"center",prop:"createTime",width:"180"},{default:a(n=>[m("span",null,C(l.parseTime(n.row.createTime)),1)]),_:1})]),_:1},8,["data"])),[[z,o(g)]]),y(t(A,{total:o(v),page:o(u),"onUpdate:page":e[2]||(e[2]=n=>V(u)?u.value=n:null),limit:o(d),"onUpdate:limit":e[3]||(e[3]=n=>V(d)?d.value=n:null)},null,8,["total","page","limit"]),[[H,o(v)>0]]),t(N,{"label-width":"100px"},{default:a(()=>[m("div",X,[t(R,{type:"primary",onClick:e[4]||(e[4]=n=>D())},{default:a(()=>[S("提交")]),_:1}),t(R,{onClick:e[5]||(e[5]=n=>w())},{default:a(()=>[S("返回")]),_:1})])]),_:1})])}}});export{oe as default};
