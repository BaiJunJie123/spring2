<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/11/18
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>任务作业</title>
    <!-- Font Awesome -->
<%--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">--%>
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="/static/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="/static/css/style.min.css" rel="stylesheet">

    <meta name="baidu-tc-cerfication" content="52031c379393b942ce0d59195791f366" />
    <link href="/static/Cron/themes/bootstrap/easyui.min.css" rel="stylesheet" type="text/css" />




    <style>
        .line
        {
            height: 25px;
            line-height: 25px;
            margin: 3px;
        }
        .imp
        {
            padding-left: 25px;
        }
        .col
        {
            width: 95px;
        }
        .easyui-layout ul {
            list-style:none;
            padding-left:10px;
        }
        .easyui-layout li {
            height:20px;
        }
    .b-1{
        margin-top: 10px;
        margin-bottom: 10px;
        margin-right: 10px;
        float: right;
        background-color: green;
        border: 1px solid green;
        border-radius: 20px;
        color: #ffffff;
        font-weight: 400;
    }
        .b-2{
            margin-left: 6px;
            background-color: green;
            border: 1px solid green;
            color: #ffffff;
        }
        #smtd{
            max-width: 100px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .x-1{

            border: 1px solid green;
            margin-left: 5px;
            margin-right: 5px;
            background-color: green;
            color: #ffffff;
            font-weight: 400;
            border-radius: 20px;

        }
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: white;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=88);
    }
    .fudong{
            display: none;
            position: absolute;
        top: 15%;
        left: 15%;
        width: 75%;
        height: 75%;
        padding: 20px;
        border: 5px solid black;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
        .fudong2{
            /*display: none;*/
            position: absolute;
            top: 15%;
            left: 15%;
            width: 75%;
            height: 75%;
            padding: 20px;
            border: 5px solid black;
            background-color: white;
            z-index:1002;
            overflow: auto;
        }
        .fudong3{
            /*display: none;*/
            position: absolute;
            top: 15%;
            left: 15%;
            width: 75%;
            height: 75%;
            padding: 20px;
            border: 5px solid black;
            background-color: white;
            z-index:1002;
            overflow: auto;
        }
    .span1{
       margin-left: 10px;
    }
        .in-1{
            margin-left: 32px;
            margin-top: 10px;
        }
        .v-2{
            width: 100%;
            height: 20%;
            margin-top: 30px;
            margin-left: 30px;

        }
        .b-4{
           margin-left: 50px;
            width: 200px;
            height: 35px;
            text-align: center;
            align-items: center;
        }
        .c-1{
            width:830px;height:560px;
            border: 1px rgb(202, 196, 196) solid;
            border-radius: 5px;
        }
    </style>
</head>

<body class="grey lighten-3">

<!--Main Navigation-->
<header>
    <!-- Sidebar -->
    <div class="sidebar-fixed position-fixed">

        <a class="logo-wrapper waves-effect">
<%--            <img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">--%>
        </a>

        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item active waves-effect">
                <i class="fas fa-chart-pie mr-3"></i>任务作业
            </a>

        </div>

    </div>
    <!-- Sidebar -->

</header>
<!--Main Navigation-->

<!--Main layout-->
<main class="pt-5 mx-lg-5">
    <div class="container-fluid mt-5">

        <!--Grid row-->
        <div class="row wow fadeIn">
            <!--Grid column-->
            <div class="col-md-12 mb-8">

                <!--Card-->
                <div class="card">


                    <!--Card content-->
                    <div class="card-body">
                        <h4>作业调度 定时器</h4>
                        <div>
                            <p id="p-1"></p>
                            <button class="b-1" onclick="stop()">暂停调度器</button>
                            <button class="b-1" onclick="qidong()">启动调度器</button>
                            <button class="b-1" onclick="shuxin()">刷新页面</button>
                        <%--  <button class="b-1" onclick="log()">调度日志</button>--%>
                            <button class="b-1" onclick="add()">新增任务</button>
                        </div>



                        <!-- Table  -->
                        <table class="table table-hover">
                            <!-- Table head -->
                            <thead class="blue lighten-4">
                            <tr>
                                <th>序号</th>
                                <th>任务名称</th>
                                <th>任务分组</th>
                                <th>任务描述</th>
                                <th>执行类</th>
                                <th>周期表达式</th>
                                <th>上次运行时间</th>
                                <th>下次运行时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!-- Table head -->

                            <!-- Table body -->
                            <tbody id="jobadd">
<%--                            <tr>--%>
<%--                                <th scope="row">1</th>--%>
<%--                                <td>Cell 1</td>--%>
<%--                                <td>Cell 2</td>--%>
<%--                                <td>Cell 3</td>--%>
<%--                                <td>Cell 3</td>--%>
<%--                                <td>Cell 3</td>--%>
<%--                                <td>Cell 3</td>--%>
<%--                                <td>Cell 3</td>--%>
<%--                            </tr>--%>
                            </tbody>
                            <!-- Table body -->
                        </table>
                        <!-- Table  -->

                    </div>

                </div>
                <!--/.Card-->

            </div>
            <!--Grid column-->

        </div>
        <!--Grid row-->

        <div id="fudong" class="fudong">

            <button class="b-1" id="close">X</button>
                        <div id="d-1">

                        </div>


        </div>

        <div id="fudong2" class="fudong2">

            <button class="b-1" id="close2">X</button>
            <div id="addjobs-1">

                <span class="span1">任务名称:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="jobname" class="in-1"/>
                <span class="span1">任务分组:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="jobgroup"  class="in-1"/>
                <span class="span1">执行类:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="zhixingClass" class="in-1"/><br/>


            </div>
            <span class="span1">周期表达式: </span>
            <div class="easyui-layout" style="width:830px;height:560px; border: 1px rgb(202, 196, 196) solid;
            border-radius: 5px; margin-left: 120px;">

                <div style="height: 100%;">
                    <div class="easyui-tabs" data-options="fit:true,border:false">
                        <div title="秒">
                            <div class="line">
                                <input type="radio" checked="checked" name="second" onclick="everyTime(this)">
                                每秒 允许的通配符[, - * /]</div>
                            <div class="line">
                                <input type="radio" name="second" onclick="cycle(this)">
                                周期从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:58" value="1"
                                       id="secondStart_0">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:59" value="2"
                                       id="secondEnd_0">
                                秒</div>
                            <div class="line">
                                <input type="radio" name="second" onclick="startOn(this)">
                                从
                                <input class="numberspinner" style="width: 60px;" data-options="min:0,max:59" value="0"
                                       id="secondStart_1">
                                秒开始,每
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:59" value="1"
                                       id="secondEnd_1">
                                秒执行一次</div>
                            <div class="line">
                                <input type="radio" name="second" id="sencond_appoint">
                                指定</div>
                            <div class="imp secondList">
                                <input type="checkbox" value="0">00
                                <input type="checkbox" value="1">01
                                <input type="checkbox" value="2">02
                                <input type="checkbox" value="3">03
                                <input type="checkbox" value="4">04
                                <input type="checkbox" value="5">05
                                <input type="checkbox" value="6">06
                                <input type="checkbox" value="7">07
                                <input type="checkbox" value="8">08
                                <input type="checkbox" value="9">09
                            </div>
                            <div class="imp secondList">
                                <input type="checkbox" value="10">10
                                <input type="checkbox" value="11">11
                                <input type="checkbox" value="12">12
                                <input type="checkbox" value="13">13
                                <input type="checkbox" value="14">14
                                <input type="checkbox" value="15">15
                                <input type="checkbox" value="16">16
                                <input type="checkbox" value="17">17
                                <input type="checkbox" value="18">18
                                <input type="checkbox" value="19">19
                            </div>
                            <div class="imp secondList">
                                <input type="checkbox" value="20">20
                                <input type="checkbox" value="21">21
                                <input type="checkbox" value="22">22
                                <input type="checkbox" value="23">23
                                <input type="checkbox" value="24">24
                                <input type="checkbox" value="25">25
                                <input type="checkbox" value="26">26
                                <input type="checkbox" value="27">27
                                <input type="checkbox" value="28">28
                                <input type="checkbox" value="29">29
                            </div>
                            <div class="imp secondList">
                                <input type="checkbox" value="30">30
                                <input type="checkbox" value="31">31
                                <input type="checkbox" value="32">32
                                <input type="checkbox" value="33">33
                                <input type="checkbox" value="34">34
                                <input type="checkbox" value="35">35
                                <input type="checkbox" value="36">36
                                <input type="checkbox" value="37">37
                                <input type="checkbox" value="38">38
                                <input type="checkbox" value="39">39
                            </div>
                            <div class="imp secondList">
                                <input type="checkbox" value="40">40
                                <input type="checkbox" value="41">41
                                <input type="checkbox" value="42">42
                                <input type="checkbox" value="43">43
                                <input type="checkbox" value="44">44
                                <input type="checkbox" value="45">45
                                <input type="checkbox" value="46">46
                                <input type="checkbox" value="47">47
                                <input type="checkbox" value="48">48
                                <input type="checkbox" value="49">49
                            </div>
                            <div class="imp secondList">
                                <input type="checkbox" value="50">50
                                <input type="checkbox" value="51">51
                                <input type="checkbox" value="52">52
                                <input type="checkbox" value="53">53
                                <input type="checkbox" value="54">54
                                <input type="checkbox" value="55">55
                                <input type="checkbox" value="56">56
                                <input type="checkbox" value="57">57
                                <input type="checkbox" value="58">58
                                <input type="checkbox" value="59">59
                            </div>
                        </div>
                        <div title="分钟">
                            <div class="line">
                                <input type="radio" checked="checked" name="min" onclick="everyTime(this)">
                                分钟 允许的通配符[, - * /]</div>
                            <div class="line">
                                <input type="radio" name="min" onclick="cycle(this)">
                                周期从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:58" value="1"
                                       id="minStart_0">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:59" value="2"
                                       id="minEnd_0">
                                分钟</div>
                            <div class="line">
                                <input type="radio" name="min" onclick="startOn(this)">
                                从
                                <input class="numberspinner" style="width: 60px;" data-options="min:0,max:59" value="0"
                                       id="minStart_1">
                                分钟开始,每
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:59" value="1"
                                       id="minEnd_1">
                                分钟执行一次</div>
                            <div class="line">
                                <input type="radio" name="min" id="min_appoint">
                                指定</div>
                            <div class="imp minList">
                                <input type="checkbox" value="0">00
                                <input type="checkbox" value="1">01
                                <input type="checkbox" value="2">02
                                <input type="checkbox" value="3">03
                                <input type="checkbox" value="4">04
                                <input type="checkbox" value="5">05
                                <input type="checkbox" value="6">06
                                <input type="checkbox" value="7">07
                                <input type="checkbox" value="8">08
                                <input type="checkbox" value="9">09
                            </div>
                            <div class="imp minList">
                                <input type="checkbox" value="10">10
                                <input type="checkbox" value="11">11
                                <input type="checkbox" value="12">12
                                <input type="checkbox" value="13">13
                                <input type="checkbox" value="14">14
                                <input type="checkbox" value="15">15
                                <input type="checkbox" value="16">16
                                <input type="checkbox" value="17">17
                                <input type="checkbox" value="18">18
                                <input type="checkbox" value="19">19
                            </div>
                            <div class="imp minList">
                                <input type="checkbox" value="20">20
                                <input type="checkbox" value="21">21
                                <input type="checkbox" value="22">22
                                <input type="checkbox" value="23">23
                                <input type="checkbox" value="24">24
                                <input type="checkbox" value="25">25
                                <input type="checkbox" value="26">26
                                <input type="checkbox" value="27">27
                                <input type="checkbox" value="28">28
                                <input type="checkbox" value="29">29
                            </div>
                            <div class="imp minList">
                                <input type="checkbox" value="30">30
                                <input type="checkbox" value="31">31
                                <input type="checkbox" value="32">32
                                <input type="checkbox" value="33">33
                                <input type="checkbox" value="34">34
                                <input type="checkbox" value="35">35
                                <input type="checkbox" value="36">36
                                <input type="checkbox" value="37">37
                                <input type="checkbox" value="38">38
                                <input type="checkbox" value="39">39
                            </div>
                            <div class="imp minList">
                                <input type="checkbox" value="40">40
                                <input type="checkbox" value="41">41
                                <input type="checkbox" value="42">42
                                <input type="checkbox" value="43">43
                                <input type="checkbox" value="44">44
                                <input type="checkbox" value="45">45
                                <input type="checkbox" value="46">46
                                <input type="checkbox" value="47">47
                                <input type="checkbox" value="48">48
                                <input type="checkbox" value="49">49
                            </div>
                            <div class="imp minList">
                                <input type="checkbox" value="50">50
                                <input type="checkbox" value="51">51
                                <input type="checkbox" value="52">52
                                <input type="checkbox" value="53">53
                                <input type="checkbox" value="54">54
                                <input type="checkbox" value="55">55
                                <input type="checkbox" value="56">56
                                <input type="checkbox" value="57">57
                                <input type="checkbox" value="58">58
                                <input type="checkbox" value="59">59
                            </div>
                        </div>
                        <div title="小时">
                            <div class="line">
                                <input type="radio" checked="checked" name="hour" onclick="everyTime(this)">
                                小时 允许的通配符[, - * /]</div>
                            <div class="line">
                                <input type="radio" name="hour" onclick="cycle(this)">
                                周期从
                                <input class="numberspinner" style="width: 60px;" data-options="min:0,max:23" value="0"
                                       id="hourStart_0">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:23" value="2"
                                       id="hourEnd_1">
                                小时</div>
                            <div class="line">
                                <input type="radio" name="hour" onclick="startOn(this)">
                                从
                                <input class="numberspinner" style="width: 60px;" data-options="min:0,max:23" value="0"
                                       id="hourStart_1">
                                小时开始,每
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:23" value="1"
                                       id="hourEnd_1">
                                小时执行一次</div>
                            <div class="line">
                                <input type="radio" name="hour" id="hour_appoint">
                                指定</div>
                            <div class="imp hourList">
                                AM:
                                <input type="checkbox" value="0">00
                                <input type="checkbox" value="1">01
                                <input type="checkbox" value="2">02
                                <input type="checkbox" value="3">03
                                <input type="checkbox" value="4">04
                                <input type="checkbox" value="5">05
                                <input type="checkbox" value="6">06
                                <input type="checkbox" value="7">07
                                <input type="checkbox" value="8">08
                                <input type="checkbox" value="9">09
                                <input type="checkbox" value="10">10
                                <input type="checkbox" value="11">11
                            </div>
                            <div class="imp hourList">
                                PM:
                                <input type="checkbox" value="12">12
                                <input type="checkbox" value="13">13
                                <input type="checkbox" value="14">14
                                <input type="checkbox" value="15">15
                                <input type="checkbox" value="16">16
                                <input type="checkbox" value="17">17
                                <input type="checkbox" value="18">18
                                <input type="checkbox" value="19">19
                                <input type="checkbox" value="20">20
                                <input type="checkbox" value="21">21
                                <input type="checkbox" value="22">22
                                <input type="checkbox" value="23">23
                            </div>
                        </div>
                        <div title="日">
                            <div class="line">
                                <input type="radio" checked="checked" name="day" onclick="everyTime(this)">
                                日 允许的通配符[, - * / L W]</div>
                            <div class="line">
                                <input type="radio" name="day" onclick="unAppoint(this)">
                                不指定</div>
                            <div class="line">
                                <input type="radio" name="day" onclick="cycle(this)">
                                周期从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                       id="dayStart_0">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:31" value="2"
                                       id="dayEnd_0">
                                日</div>
                            <div class="line">
                                <input type="radio" name="day" onclick="startOn(this)">
                                从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                       id="dayStart_1">
                                日开始,每
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                       id="dayEnd_1">
                                天执行一次</div>
                            <div class="line">
                                <input type="radio" name="day" onclick="workDay(this)">
                                每月
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                       id="dayStart_2">
                                号最近的那个工作日</div>
                            <div class="line">
                                <input type="radio" name="day" onclick="lastDay(this)">
                                本月最后一天</div>
                            <div class="line">
                                <input type="radio" name="day" id="day_appoint">
                                指定</div>
                            <div class="imp dayList">
                                <input type="checkbox" value="1">1
                                <input type="checkbox" value="2">2
                                <input type="checkbox" value="3">3
                                <input type="checkbox" value="4">4
                                <input type="checkbox" value="5">5
                                <input type="checkbox" value="6">6
                                <input type="checkbox" value="7">7
                                <input type="checkbox" value="8">8
                                <input type="checkbox" value="9">9
                                <input type="checkbox" value="10">10
                                <input type="checkbox" value="11">11
                                <input type="checkbox" value="12">12
                                <input type="checkbox" value="13">13
                                <input type="checkbox" value="14">14
                                <input type="checkbox" value="15">15
                                <input type="checkbox" value="16">16
                            </div>
                            <div class="imp dayList">
                                <input type="checkbox" value="17">17
                                <input type="checkbox" value="18">18
                                <input type="checkbox" value="19">19
                                <input type="checkbox" value="20">20
                                <input type="checkbox" value="21">21
                                <input type="checkbox" value="22">22
                                <input type="checkbox" value="23">23
                                <input type="checkbox" value="24">24
                                <input type="checkbox" value="25">25
                                <input type="checkbox" value="26">26
                                <input type="checkbox" value="27">27
                                <input type="checkbox" value="28">28
                                <input type="checkbox" value="29">29
                                <input type="checkbox" value="30">30
                                <input type="checkbox" value="31">31
                            </div>
                        </div>
                        <div title="月">
                            <div class="line">
                                <input type="radio" checked="checked" name="mouth" onclick="everyTime(this)">
                                月 允许的通配符[, - * /]</div>
                            <div class="line">
                                <input type="radio" name="mouth" onclick="unAppoint(this)">
                                不指定</div>
                            <div class="line">
                                <input type="radio" name="mouth" onclick="cycle(this)">
                                周期从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                       id="mouthStart_0">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:12" value="2"
                                       id="mouthEnd_0">
                                月</div>
                            <div class="line">
                                <input type="radio" name="mouth" onclick="startOn(this)">
                                从
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                       id="mouthStart_1">
                                日开始,每
                                <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                       id="mouthEnd_1">
                                月执行一次</div>
                            <div class="line">
                                <input type="radio" name="mouth" id="mouth_appoint">
                                指定</div>
                            <div class="imp mouthList">
                                <input type="checkbox" value="1">1
                                <input type="checkbox" value="2">2
                                <input type="checkbox" value="3">3
                                <input type="checkbox" value="4">4
                                <input type="checkbox" value="5">5
                                <input type="checkbox" value="6">6
                                <input type="checkbox" value="7">7
                                <input type="checkbox" value="8">8
                                <input type="checkbox" value="9">9
                                <input type="checkbox" value="10">10
                                <input type="checkbox" value="11">11
                                <input type="checkbox" value="12">12
                            </div>
                        </div>
                        <div title="周">
                            <div class="line">
                                <input type="radio" checked="checked" name="week" onclick="everyTime(this)">
                                周 允许的通配符[, - * / L #]</div>
                            <div class="line">
                                <input type="radio" name="week" onclick="unAppoint(this)">
                                不指定</div>
                            <div class="line">
                                <input type="radio" name="week" onclick="startOn(this)">
                                周期 从星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                             id="weekStart_0" value="1">
                                -
                                <input class="numberspinner" style="width: 60px;" data-options="min:2,max:7" value="2"
                                       id="weekEnd_0"></div>
                            <div class="line">
                                <input type="radio" name="week" onclick="weekOfDay(this)">
                                第<input class="numberspinner" style="width: 60px;" data-options="min:1,max:4" value="1"
                                        id="weekStart_1">
                                周 的星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                            id="weekEnd_1" value="1"></div>
                            <div class="line">
                                <input type="radio" name="week" onclick="lastWeek(this)">
                                本月最后一个星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                               id="weekStart_2" value="1"></div>
                            <div class="line">
                                <input type="radio" name="week" id="week_appoint">
                                指定</div>
                            <div class="imp weekList">
                                <input type="checkbox" value="1">1
                                <input type="checkbox" value="2">2
                                <input type="checkbox" value="3">3
                                <input type="checkbox" value="4">4
                                <input type="checkbox" value="5">5
                                <input type="checkbox" value="6">6
                                <input type="checkbox" value="7">7
                            </div>
                        </div>
                        <div title="年">
                            <div class="line">
                                <input type="radio" checked="checked" name="year" onclick="unAppoint(this)">
                                不指定 允许的通配符[, - * /] 非必填</div>
                            <div class="line">
                                <input type="radio" name="year" onclick="everyTime(this)">
                                每年</div>
                            <div class="line">
                                <input type="radio" name="year" onclick="cycle(this)">周期 从
                                <input class="numberspinner" style="width: 90px;" data-options="min:2013,max:3000"
                                       id="yearStart_0" value="2013">
                                -
                                <input class="numberspinner" style="width: 90px;" data-options="min:2014,max:3000"
                                       id="yearEnd_0" value="2014"></div>
                        </div>
                    </div>
                </div>
                <div data-options="region:'south',border:false" style="height:250px">
                    <fieldset style="border-radius: 3px; height: 220px;">
                        <legend>表达式</legend>
                        <table style="height: 100px;">
                            <tbody>
                            <tr>
                                <td>
                                </td>
                                <td align="center">
                                    秒
                                </td>
                                <td align="center">
                                    分钟
                                </td>
                                <td align="center">
                                    小时
                                </td>
                                <td align="center">
                                    日
                                </td>
                                <td align="center">
                                    月<br />
                                </td>
                                <td align="center">
                                    星期
                                </td>
                                <td align="center">
                                    年
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    表达式字段:
                                </td>
                                <td>
                                    <input type="text" name="v_second" class="col" value="*" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_min" class="col" value="*" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_hour" class="col" value="*" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_day" class="col" value="*" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_mouth" class="col" value="*" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_week" class="col" value="?" readonly="readonly" />
                                </td>
                                <td>
                                    <input type="text" name="v_year" class="col" readonly="readonly" />
                                </td>
                            </tr>
                            <tr>
                                <td>Cron 表达式:</td>
                                <td colspan="6"><input type="text" name="cron" style="width: 100%;" value="* * * * * ?" id="cron"
                                /></td>
                                <td><input type="button"  value="  确 认 修 改 " id="btnFan" onclick="trueUpdate()"/></td>
                            </tr>

                            </tbody>
                        </table>
                    </fieldset>
                    <div style="text-align: center; margin-top: 5px;">
                        <script type="text/javascript">
                            /*killIe*/

                            $.parser.parse($("body"));
                            var cpro_id = "u1331261";

                            function btnFan() {
                                //获取参数中表达式的值
                                var txt = $("#cron").val();
                                if (txt) {
                                    var regs = txt.split(' ');
                                    $("input[name=v_second]").val(regs[0]);
                                    $("input[name=v_min]").val(regs[1]);
                                    $("input[name=v_hour]").val(regs[2]);
                                    $("input[name=v_day]").val(regs[3]);
                                    $("input[name=v_mouth]").val(regs[4]);
                                    $("input[name=v_week]").val(regs[5]);

                                    initObj(regs[0], "second");
                                    initObj(regs[1], "min");
                                    initObj(regs[2], "hour");
                                    initDay(regs[3]);
                                    initMonth(regs[4]);
                                    initWeek(regs[5]);

                                    if (regs.length > 6) {
                                        $("input[name=v_year]").val(regs[6]);
                                        initYear(regs[6]);
                                    }
                                }
                            }


                            function initObj(strVal, strid) {
                                var ary = null;
                                var objRadio = $("input[name='" + strid + "'");
                                if (strVal == "*") {
                                    objRadio.eq(0).attr("checked", "checked");
                                } else if (strVal.split('-').length > 1) {
                                    ary = strVal.split('-');
                                    objRadio.eq(1).attr("checked", "checked");
                                    $("#" + strid + "Start_0").numberspinner('setValue', ary[0]);
                                    $("#" + strid + "End_0").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('/').length > 1) {
                                    ary = strVal.split('/');
                                    objRadio.eq(2).attr("checked", "checked");
                                    $("#" + strid + "Start_1").numberspinner('setValue', ary[0]);
                                    $("#" + strid + "End_1").numberspinner('setValue', ary[1]);
                                } else {
                                    objRadio.eq(3).attr("checked", "checked");
                                    if (strVal != "?") {
                                        ary = strVal.split(",");
                                        for (var i = 0; i < ary.length; i++) {
                                            $("." + strid + "List input[value='" + ary[i] + "']").attr("checked", "checked");
                                        }
                                    }
                                }
                            }

                            function initDay(strVal) {
                                var ary = null;
                                var objRadio = $("input[name='day'");
                                if (strVal == "*") {
                                    objRadio.eq(0).attr("checked", "checked");
                                } else if (strVal == "?") {
                                    objRadio.eq(1).attr("checked", "checked");
                                } else if (strVal.split('-').length > 1) {
                                    ary = strVal.split('-');
                                    objRadio.eq(2).attr("checked", "checked");
                                    $("#dayStart_0").numberspinner('setValue', ary[0]);
                                    $("#dayEnd_0").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('/').length > 1) {
                                    ary = strVal.split('/');
                                    objRadio.eq(3).attr("checked", "checked");
                                    $("#dayStart_1").numberspinner('setValue', ary[0]);
                                    $("#dayEnd_1").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('W').length > 1) {
                                    ary = strVal.split('W');
                                    objRadio.eq(4).attr("checked", "checked");
                                    $("#dayStart_2").numberspinner('setValue', ary[0]);
                                } else if (strVal == "L") {
                                    objRadio.eq(5).attr("checked", "checked");
                                } else {
                                    objRadio.eq(6).attr("checked", "checked");
                                    ary = strVal.split(",");
                                    for (var i = 0; i < ary.length; i++) {
                                        $(".dayList input[value='" + ary[i] + "']").attr("checked", "checked");
                                    }
                                }
                            }

                            function initMonth(strVal) {
                                var ary = null;
                                var objRadio = $("input[name='mouth'");
                                if (strVal == "*") {
                                    objRadio.eq(0).attr("checked", "checked");
                                } else if (strVal == "?") {
                                    objRadio.eq(1).attr("checked", "checked");
                                } else if (strVal.split('-').length > 1) {
                                    ary = strVal.split('-');
                                    objRadio.eq(2).attr("checked", "checked");
                                    $("#mouthStart_0").numberspinner('setValue', ary[0]);
                                    $("#mouthEnd_0").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('/').length > 1) {
                                    ary = strVal.split('/');
                                    objRadio.eq(3).attr("checked", "checked");
                                    $("#mouthStart_1").numberspinner('setValue', ary[0]);
                                    $("#mouthEnd_1").numberspinner('setValue', ary[1]);

                                } else {
                                    objRadio.eq(4).attr("checked", "checked");

                                    ary = strVal.split(",");
                                    for (var i = 0; i < ary.length; i++) {
                                        $(".mouthList input[value='" + ary[i] + "']").attr("checked", "checked");
                                    }
                                }
                            }

                            function initWeek(strVal) {
                                var ary = null;
                                var objRadio = $("input[name='week'");
                                if (strVal == "*") {
                                    objRadio.eq(0).attr("checked", "checked");
                                } else if (strVal == "?") {
                                    objRadio.eq(1).attr("checked", "checked");
                                } else if (strVal.split('/').length > 1) {
                                    ary = strVal.split('/');
                                    objRadio.eq(2).attr("checked", "checked");
                                    $("#weekStart_0").numberspinner('setValue', ary[0]);
                                    $("#weekEnd_0").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('-').length > 1) {
                                    ary = strVal.split('-');
                                    objRadio.eq(3).attr("checked", "checked");
                                    $("#weekStart_1").numberspinner('setValue', ary[0]);
                                    $("#weekEnd_1").numberspinner('setValue', ary[1]);
                                } else if (strVal.split('L').length > 1) {
                                    ary = strVal.split('L');
                                    objRadio.eq(4).attr("checked", "checked");
                                    $("#weekStart_2").numberspinner('setValue', ary[0]);
                                } else {
                                    objRadio.eq(5).attr("checked", "checked");
                                    ary = strVal.split(",");
                                    for (var i = 0; i < ary.length; i++) {
                                        $(".weekList input[value='" + ary[i] + "']").attr("checked", "checked");
                                    }
                                }
                            }

                            function initYear(strVal) {
                                var ary = null;
                                var objRadio = $("input[name='year'");
                                if (strVal == "*") {
                                    objRadio.eq(1).attr("checked", "checked");
                                } else if (strVal.split('-').length > 1) {
                                    ary = strVal.split('-');
                                    objRadio.eq(2).attr("checked", "checked");
                                    $("#yearStart_0").numberspinner('setValue', ary[0]);
                                    $("#yearEnd_0").numberspinner('setValue', ary[1]);
                                }
                            }

                        </script>
                        <div>
                        </div>
                    </div>
                </div>
                <div>
                </div>

            </div>
            <div id="addjobs-2">
                <span class="span1">任务描述:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="note" class="in-1"/>
                <span class="span1">定时器组:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="triggerGroup" class="in-1"/>
                <span class="span1">定时器名称: </span><input type="text" name="triggerName" class="in-1"/>
                <button onclick="addJob()" class="b-4">确认添加</button>

            </div>

        </div>

        <div id="fade" class="black_overlay"></div>



        <!--Footer-->
<footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">



</footer>
<!--/.Footer-->

<!-- SCRIPTS -->
<!-- JQuery -->
        <script src="/static/Cron/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="/static/Cron/jquery.easyui.min.js" type="text/javascript"></script>
        <script src="/static/Cron/cron.js" type="text/javascript"></script>
<%--<script type="text/javascript" src="/static/js/jquery-3.4.1.min.js"></script>--%>
<%--<script type="text/javascript" src="/static/js/jquery-1.12.4.js"></script>--%>
<!-- Bootstrap tooltips -->
<%--<script type="text/javascript" src="/static/js/popper.min.js"></script>--%>
<!-- Bootstrap core JavaScript -->
<%--<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>--%>
<!-- MDB core JavaScript -->
<%--<script type="text/javascript" src="/static/js/mdb.min.js"></script>--%>

<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();

</script>

<!-- ajax 此处开始 -->
<script>
    var list;
    $(document).ready(function () {
        //一打开加载

        $("#fudong2").hide();
        shuxin();
        isshutDown();
    });
    //启动
    function qidong() {
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1:3003/startAllJob',
            data:null,
            dataType: 'json',
            success: function (data) {
                shuxin();
                isshutDown();
                alert(data.status);
            },
            error:function () {
                alert("失败")
            }

        });
    }
    //暂停
    function zanting(e) {
        var data = this.list[e];
        var jobname = data.jobName;
        var jobgroup = data.jobGroup;
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1:3003/stop',
            data: {"jobName1":jobname,"jobGroupName1":jobgroup},
            dataType: 'json',
            success: function (data) {
                shuxin();
                isshutDown();
                alert(data.status);
            },
            error:function () {
                alert("失败")
            }

        });
    }
    //删除
    function deleteforjob(e) {
        var data = this.list[e];
        var zu = {"jobName1":data.jobName,"jobGroupName1":data.jobGroup,"triggerName1":data.triggerName,"triggerGroupName1":data.triggerGroup};
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1:3003/delete',
            data: zu,
            dataType: 'json',
            success: function (data) {
                shuxin();
                alert(data.status);
            },
            error:function () {
                alert("失败")
            }

        });
    }
    //启动
    function start(e){
        var data = this.list[e];
        var jobname = data.jobName;
        var jobgroup = data.jobGroup;
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1:3003/start',
            data: {"jobName1":jobname,"jobGroupName1":jobgroup},
            dataType: 'json',
            success: function (data) {
                shuxin();
                alert(data.status);
            },
            error:function () {
                alert("失败")
            }

        });

    }

   function stop() {
       $.ajax({
           type: 'POST',
           url: 'http://127.0.0.1:3003/closeAllJob',
           data:null,
           dataType: 'json',
           success: function (data) {
               if(data.status == 'stopOk'){
                   $("#p-1").html("调度器状态：已暂停")
               }
                alert(data.status);
           },
           error:function () {
               alert("失败")
           }

       });
   }
   function  isshutDown() {
       $.ajax({
           type: 'POST',
           url: 'http://127.0.0.1:3003/isStarted',
           data:null,
           dataType: 'json',
           success: function (data) {
               var zhuang = "";
               if(data.status == 'false'){
                   zhuang = "已停止";
               }else{
                   zhuang = "运行中";
               }
               $("#p-1").html("调度器状态："+zhuang)
           },
           error:function () {
               alert("失败")
           }

       });
   }
   function shuxin() {
       $("#jobadd").find("tr").remove();
       $.ajax({
           type: 'POST',
           url: 'http://127.0.0.1:3003/getAllJob',
           data: null,
           dataType: 'json',
           success: function (data) {
               list = data;
               console.log(data);
               for (var i = 0; i < data.length;i++){
                   $("#jobadd").append('<tr><th scope=\"row\">'+(i+1)+'</th>' +
                       '<td id="smtd">'+data[i].jobName+'</td><td id="smtd">'+data[i].jobGroup+'</td>' +
                       '<td id="smtd">'+data[i].jobNote+'</td><td id="smtd">'+data[i].schedulingForString+'</td>' +
                       '<td id="smtd">'+data[i].cron+'</td><td id="smtd">'+data[i].useTimeAgo+'</td>' +
                       '<td id="smtd">'+data[i].useTimeNext+'</td><td id="smtd">'+data[i].status+'</td>' +
                       '<td><button  class="x-1" onclick="start('+i+')">启动</button><button onclick="zanting('+i+')"  class="x-1">暂停</button><button onclick="updates('+i+')" class="x-1">修改</button><button onclick="deleteforjob('+i+')"  class="x-1">删除</button><button onclick="findInfo('+i+')" class="x-1">查看</button></td></tr>');

               }
               //alert(data);
           },
           error:function () {
               alert("失败")
           }

       });
   }
   var golbalUpdateIndex = 0;
   function updates(e) {
       golbalUpdateIndex = e;
       $("#addjobs-1").hide();
       $("#addjobs-2").hide();
       $("#btnFan").show()
       $("#fudong2").show();
       $("#fade").show();
   }
   function trueUpdate() {
       var cron = $("#cron").val();
       var data = this.list[golbalUpdateIndex];
       var triggerName = data.triggerName;
       var triggerGroup = data.triggerGroup;
       $.ajax({
           type: 'POST',
           url: 'http://127.0.0.1:3003/updateCron',
           data: {"tirggerName":triggerName,"tirggerGroup":triggerGroup,"newCron":cron},
           dataType: 'json',
           success: function (data) {
               $("#fudong2").hide();
               $("#fade").hide();
                shuxin();
                alert(data.status);
           },
           error:function () {
               alert("失败")
           }

       });


   }
   function log() {
       alert("日志");
   }
   function addJob() {
       var jobname = $("input[name='jobname']").val();
       var jogroup = $("input[name='jobgroup']").val();
       var cron = $("input[name='cron']").val();
       var zhixingclass = $("input[name='zhixingClass']").val();
       var note = $("input[name='note']").val();
       var triggername = $("input[name='triggerName']").val();
       var triggerGroup = $("input[name='triggerGroup']").val();
       if(jobname!= "" && jogroup!= "" && cron!= "" && zhixingclass!= "" && triggername!= "" && triggerGroup!= ""){
           var zu = {"jobName1":jobname,"jobGroupName1":jogroup,"cron":cron,"classUrlName":zhixingclass,"note":note,"triggerName1":triggername,"triggerGroupName1":triggerGroup};
           $.ajax({
               type: 'POST',
               url: 'http://127.0.0.1:3003/addJob',
               data: zu,
               dataType: 'json',
               success: function (data) {
                   $("#fudong2").hide();
                   $("#fade").hide();
                   shuxin();
                   alert(data.status);
               },
               error:function () {
                   alert("失败")
               }

           });
       }else{
           alert("请完善信息");
       }


   }
   function add() {
       // $("#d-1").find("div").remove();
       // $("#d-1").append('<div><span class="span1">任务名称:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="jobname" class="in-1"/><br/>' +
       //     '<span class="span1">任务分组:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="jobgroup"  class="in-1"/><br/>' +
       //     '<span class="span1">周期表达式: </span><input type="text" name="cron" class="in-1"/><br/>' +
       //     '<span class="span1">执行类:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="zhixingClass" class="in-1"/><br/>' +
       //     '<span class="span1">任务描述:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="note" class="in-1"/><br/>' +
       //     '' +
       //     '<span class="span1">定时器组:&nbsp;&nbsp;&nbsp;&nbsp; </span><input type="text" name="triggerGroup" class="in-1"/><br/>' +
       //     '<span class="span1">定时器名称: </span><input type="text" name="triggerName" class="in-1"/></div>');
       // $("#d-1").append("<div class='v-2'><button onclick='addJob()' class='b-4'>确认添加</button></div>")
       // $("#fudong").show();
       // $("#fade").show();
       $("#btnFan").hide()
       $("#addjobs-1").show();
       $("#addjobs-2").show();
       $("#fudong2").show();
       $("#fade").show();


   }
   //查看详情
    function findInfo(e) {
        var data = this.list[e];
        $("#d-1").find("div").remove();
        $("#d-1").append('<div><span >任务名称: </span><span>'+data.jobName+'</span><br/>' +
            '<span>任务分组: </span><span>'+data.jobGroup+'</span><br/>' +
            '<span>周期表达式: </span><span>'+data.cron+'</span><br/>' +
            '<span>执行类: </span><span>'+data.schedulingForString+'</span><br/>' +
            '<span>任务描述: </span><span>'+data.jobNote+'</span><br/>' +
            '<span>状态: </span><span>'+data.status+'</span><br/>' +
            '<span>定时器组: </span><span>'+data.triggerGroup+'</span><br/>' +
            '<span>定时器名称: </span><span>'+data.triggerName+'</span><br/>' +
            '<span>上次运行时间: </span><span>'+data.useTimeAgo+'</span><br/>' +
            '<span>下次运行时间: </span><span>'+data.useTimeNext+'</span><br/></div>');
        $("#fudong").show();
        $("#fade").show();

    }

    $("#close").click(function () {
        $("#fudong").hide();
        $("#fade").hide();
    });
    $("#close2").click(function () {
        alert("dfsdf");
        $("#fudong2").hide();
        $("#fade").hide();
    });


</script>



</body>

</html>

