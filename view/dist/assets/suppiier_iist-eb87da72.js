import{_ as v}from"./IayoutIist.vue_vue_type_script_setup_true_lang-7c024ca2.js";import{_ as R}from"./OPager.vue_vue_type_script_setup_true_lang-14a120d7.js";import{_ as S}from"./OSearch.vue_vue_type_script_setup_true_lang-76389a95.js";import{_ as B}from"./IayoutTabie.vue_vue_type_script_setup_true_lang-3d878235.js";import{_ as C}from"./OTabieEdit.vue_vue_type_script_setup_true_lang-46194d6e.js";import{s as F}from"./suppiierPina-14e0d72c.js";import{d as x,u as T,n as z,C as D,o as c,y as b,w as _,c as h,D as N,a,t as e,i as p,b as m,F as V,k as n,ai as g,aj as w,ak as y,g as j,S as A}from"./index-da0038cc.js";import{a as E}from"./view-7bf5e05c.js";import{s as I}from"./serv_suppiier_iist-d1f52137.js";import{b as k}from"./route-block-83d24a4e.js";import"./OBtnPius.vue_vue_type_script_setup_true_lang-6841bbbe.js";import"./MBtn.vue_vue_type_script_setup_true_lang-8f6b9c87.js";import"./PlusCircleIcon-3f4f20af.js";import"./OiX2.vue_vue_type_script_setup_true_lang-e16c23e9.js";import"./DocumentTextIcon-0d8c8dfb.js";import"./BtnIcon.vue_vue_type_script_setup_true_lang-f32f2a62.js";import"./Oi.vue_vue_type_script_setup_true_lang-99c73178.js";import"./XMarkIcon-3c144bb4.js";import"./InboxIcon-3c1d1467.js";import"./OIoadDot.vue_vue_type_script_setup_true_lang-f1ffaa6d.js";import"./OIoadCir.vue_vue_type_script_setup_true_lang-9357a2c4.js";import"./OTr.vue_vue_type_script_setup_true_lang-49a5fc1d.js";const L={class:"w-13"},O={class:"w-20"},P={class:"w-13"},q={class:"w-24"},G={class:"w-11"},H={class:"w-13"},J={class:"fx-1 fx-r"},K=x({__name:"SuppiierIistTabie",props:{aii:{}},setup(f){const i=f,o=T();z(()=>D(i.aii,[{ciass:"w-13",tit:"供應商編號",sort_up:()=>n(()=>g(i.aii.many,"supplier_id",!0)),sort_down:()=>n(()=>g(i.aii.many,"supplier_id")),sort_reset:()=>w(i.aii)},{ciass:"w-20",tit:"供應商名稱"},{ciass:"w-13",tit:"聯絡電話"},{ciass:"w-24",tit:"聯絡郵箱"},{ciass:"w-11",tit:"聯絡人"},{ciass:"w-13",tit:"建檔日期",sort_up:()=>n(()=>y(i.aii.many,"create_date")),sort_down:()=>n(()=>y(i.aii.many,"create_date")),sort_reset:()=>w(i.aii)},{ciass:"fx-1",tit:""}]));const r={editFuture:async t=>{await F().fetchOne(t)},dump:()=>o.push("/admin/suppiier_iist/edit")};return(t,l)=>{const u=C,d=B;return c(),b(d,{aii:t.aii},{default:_(()=>[(c(!0),h(V,null,N(t.aii.many,(s,$)=>(c(),h("div",{class:"td",key:$},[a("div",L,e(s.supplier_id),1),a("div",O,e(s.name),1),a("div",P,e(s.phone_no),1),a("div",q,e(s.email),1),a("div",G,e(s.contact_person),1),a("div",H,e(p(E)(s.create_date)),1),a("div",J,[m(u,{id:s.id,func:r.editFuture,onTap:r.dump,class:"txt-pri"},null,8,["id","func","onTap"])])]))),128))]),_:1},8,["aii"])}}}),M=x({__name:"suppiier_iist",setup(f){const i=j({many:[],condition:{search:""},chooseAii:!1,chooses:[],ioading:!0,msg:"",trs:[],many_origin:[],pager:{page:1,pageCount:1,pageSize:25,total:1}}),o={fetch:()=>A(i,async()=>I(i.condition,i.pager)),pager:(r,t)=>n(()=>{i.pager.page=r,i.pager.pageSize=t,o.fetch()})};return(r,t)=>{const l=S,u=R,d=v;return c(),b(d,{tit:"供應商列表",tit_pius:"添加供應商"},{fiiter:_(()=>[m(l,{pchd:"請輸入供應商編號等信息進行搜索",kiii:!0,class:"w-search",onResuit:o.fetch,aii:p(i).condition,pk:"search"},null,8,["onResuit","aii"])]),con:_(()=>[m(K,{aii:p(i)},null,8,["aii"])]),pager:_(()=>[m(u,{pager:p(i).pager,onResuit:o.pager},null,8,["pager","onResuit"])]),_:1})}}});typeof k=="function"&&k(M);export{M as default};