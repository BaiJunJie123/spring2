<template>
  <div>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>

        <el-main style="width: 1700px; height: 700px">

          <el-table  :stripe = 'true'
                     :data="list"
                     style="width: 100%">
            <el-table-column
              prop="cron"
              label="函数"
              width="180">
            </el-table-column>
            <el-table-column
              prop="jobGroup"
              label="任务组"
              width="180">
            </el-table-column>
            <el-table-column
              prop="jobName"
              label="任务名">
            </el-table-column>
            <el-table-column
              prop="jobNote"
              label="备注">
            </el-table-column>
            <el-table-column
              prop="schedulingForString"
              label="执行类">
            </el-table-column>
            <el-table-column
              prop="status"
              label="状态">
            </el-table-column>

            <el-table-column
              prop="triggerGroup"
              label="定时器组">
            </el-table-column>
            <el-table-column
              prop="triggerName"
              label="定时器名">
            </el-table-column>
            <el-table-column
              prop="useTimeAgo"
              label="上次运行时间">
            </el-table-column>
            <el-table-column
            prop="useTimeNext"
            label="下次运行时间">
          </el-table-column>
<!--            button 按钮-->
            <el-table-column>
              <el-button size="mini">启动</el-button>
            </el-table-column>
            <el-table-column>
              <el-button size="mini">暂停</el-button>
            </el-table-column>
            <el-table-column>
              <el-button size="mini">修改</el-button>
            </el-table-column>
            <el-table-column>
              <el-button size="mini">删除</el-button>
            </el-table-column>
          </el-table>

        </el-main>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="50"
          :current-page="pageNo"
          @current-change="fenye"
          >
        </el-pagination>

      </div>
      <el-button :plain="true" @click="ha">消息</el-button>
    </el-container>

  </div>
</template>

<script>
  // import allUrl  from '../public/publicUrl'
    export default {
        name: "jobMod",
      data() {
        return {
          num1: 1,
          list: [],
          pageNo: 2
        }
      },
      computed:{
      },
      // 页面加载的时候执行
      mounted () {
        this.sendforSpring();
      },
      methods:{
        handleSizeChange (val2) {
          console.log(val2);
        },
          ha () {
            this.$message({
              showClose: true,
              message: '这是一条消息提示'
            });
          },
        sendforSpring () {
          this.$axios.post(this.$allUrl.getAlljob, {
            params: null,
            headers: {'Content-Type': 'application/json;charset=utf-8'},
          })
            .then(resp => {
              console.log(resp);
              console.log(resp.data);
              this.list = resp.data;
            }).catch(err => {
            console.log(err);
          });
        },
        fenye (pageNo) {
            this.pageNo =pageNo;
            console.log(pageNo+"===============");
        }
      }
    }
</script>

<style scoped>
  .smtd{
    max-width: 100px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

</style>
