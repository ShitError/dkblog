<template>
  <div>
    <!-- 主页文章 -->
    <v-row class="home-container">
      <v-col md="9" cols="12">
        <v-card
          class="animated zoomIn article-card"
          style="border-radius: 2px 2px 2px 2px"
          v-for="(item, index) of articleList"
          :key="item.id"
        >
          <!-- 文章封面图 -->
          <div :class="isRight(index)">
            <router-link :to="'/articles/' + item.id">
              <v-img
                class="on-hover"
                width="100%"
                height="100%"
                :src="item.articleCover"
              />
            </router-link>
          </div>
          <!-- 文章信息 -->
          <div class="article-wrapper">
            <div style="line-height:1.4">
              <router-link :to="'/articles/' + item.id">
                {{ item.articleTitle }}
              </router-link>
            </div>
            <div class="article-info">
              <!-- 是否置顶 -->
              <span v-if="item.isTop == 1">
                <span style="color:#fae96f">
                  <i class="iconfont icon-zhiding" /> 置顶
                </span>
                <span class="separator"> </span>
              </span>

              <!-- 文章分类 -->
              <router-link :to="'/categories/' + item.categoryId">
                <v-icon size="14" style="color: rgba(91,84,217,0.4);"
                  >mdi-inbox-full-outline</v-icon
                >
                {{ item.categoryName }}
              </router-link>
              <span class="separator"> </span>
              <!-- 文章标签 -->
              <router-link
                style="display:inline-block"
                :to="'/tags/' + tag.id"
                class="mr-1"
                v-for="tag of item.tagDTOList"
                :key="tag.id"
              >
                <v-icon size="14" style="color: rgb(212,43,178,0.4)"
                  >mdi-tag-multiple</v-icon
                >{{ tag.tagName }}
                <span class="separator"> </span>
              </router-link>
            </div>
            <!-- 文章内容 -->
            <div class="article-content">
              {{ item.articleContent }}
            </div>
            <!-- 尾部信息-->
            <div style="font-size: 12px; margin-top: 7px;">
              <v-icon size="12">mdi-update</v-icon>
              发布于 {{ item.createTime | date }}
              <div class="float-right" style="font-size: 12px;">
                <i class="iconfont icon-liulan"></i>
                {{ item.viewsCount }}
                <i class="iconfont icon-pinglun1"></i>
                {{ item.commentCount }}
              </div>
            </div>
          </div>
        </v-card>
        <!-- 无限加载 -->
        <infinite-loading @infinite="infiniteHandler">
          <div slot="no-more" />
        </infinite-loading>
      </v-col>
      <!-- 博主信息 -->
      <v-col md="3" cols="12" class="d-md-block d-none">
        <div class="blog-wrapper">
          <v-card class="animated zoomIn blog-card mt-5">
            <div class="author-wrapper">
              <!-- 博主头像 -->
              <v-avatar size="110" style="margin-top: 30px">
                <img
                  class="author-avatar"
                  :src="blogInfo.websiteConfig.websiteAvatar"
                />
              </v-avatar>
              <div style="font-size: 1.375rem">
                {{ blogInfo.websiteConfig.websiteAuthor }}
              </div>
              <div style="font-size: 0.875rem;">
                {{ blogInfo.websiteConfig.websiteIntro }}
              </div>
            </div>
            <hr />
            <!-- 社交信息 -->
            <div class="card-info-social">
              <a
                v-if="isShowSocial('qq')"
                class="mr-5 iconfont icon-qq-line"
                target="_blank"
                :href="
                  'http://wpa.qq.com/msgrd?v=3&uin=' +
                    blogInfo.websiteConfig.qq +
                    '&site=qq&menu=yes'
                "
              />
              <a
                v-if="isShowSocial('github')"
                target="_blank"
                :href="blogInfo.websiteConfig.github"
                class="mr-5 iconfont icon-github-line"
              />
              <a
                v-if="isShowSocial('gitee')"
                target="_blank"
                :href="blogInfo.websiteConfig.gitee"
                class="iconfont icon-gitee"
              />
            </div>
          </v-card>
          <!-- 网站公告 -->
          <v-card class="blog-card animated zoomIn mt-5 big">
            <div class="web-info-title">
              <v-icon size="18">mdi-bullhorn-variant</v-icon>
              公告
            </div>
            <div style="font-size:0.8rem">
              {{ blogInfo.websiteConfig.websiteNotice }}
            </div>
          </v-card>
          <!-- 最热文章 -->
          <v-card
            class="blog-card animated zoomIn blog-card mt-5"
            style="margin-top:20px"
          >
            <div class="web-info-title">
              <v-icon size="22" style="color: rgba(255,0,0,0.6)"
                >mdi-fire</v-icon
              >
              <span> 最热文章</span>
            </div>
            <div class="hottest-list">
              <div
                class="hottest-item"
                v-for="item of hottestArticleList"
                :key="item.articleTitle"
              >
                <div
                  class="hottest"
                  style="display: flow; line-height: 50px ;font-size:0.85rem "
                >
                  <router-link :to="'/articles/' + item.id">
                    {{ item.articleTitle }}
                  </router-link>
                  <div class="float-right" style="font-size: 10px">
                    <v-icon size="14" style="color: rgba(255,0,0,0.4)"
                      >mdi-fire</v-icon
                    >
                    {{ item.viewsCount * 14 }}
                  </div>
                </div>
              </div>
            </div>
          </v-card>
          <!-- 标签云-->
          <v-card class="blog-card animated zoomIn mt-5">
            <div class="web-info-title">
              <v-icon size="18" style="color: #1abc9c">mdi-tag</v-icon>
              标签云
            </div>
            <tagcloud
              style="height: 250px; width: 130%; margin: -10% 0 0 -27%"
            ></tagcloud>
          </v-card>
          <!-- 网站信息 -->
          <v-card class="blog-card animated zoomIn mt-5">
            <div class="web-info-title">
              <v-icon size="18" style="color: #d7d957;">mdi-chart-line</v-icon>
              网站资讯
            </div>
            <div class="web-info">
              <div style="padding:4px 0 0">
                运行时间:<span class="float-right">{{ time }}</span>
              </div>
              <div style="padding:4px 0 0">
                文章总数:<span class="float-right">{{ blogInfo.articleCount }}</span>
              </div>
              <div style="padding:4px 0 0">
                分类总数:<span class="float-right">{{ blogInfo.categoryCount }}</span>
              </div>
              <div style="padding:4px 0 0">
                标签总数:<span class="float-right">{{ blogInfo.tagCount }}</span>
              </div>
              <div style="padding:4px 0 0">
                总访问量:<span class="float-right">
                  {{ blogInfo.viewsCount }}
                </span>
              </div>
            </div>
          </v-card>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import tagcloud from "../../components/TagWall";
export default {
  components: {
    tagcloud
  },
  created() {
    this.timer = setInterval(this.runTime, 1000);
  },
  beforeMount() {
    this.axios
      .get("/api/newest/articlelist")
      .then(res => {
        //请求成功的回调函数
        if (res.data.flag) {
          this.newestArticleList = res.data.data;
        }
      })
      .catch(err => {
        //请求失败的回调函数
        console.log(err);
      });
    this.axios
      .get("/api/hottest/articlelist")
      .then(res => {
        //请求成功的回调函数
        if (res.data.flag) {
          this.hottestArticleList = res.data.data;
        }
      })
      .catch(err => {
        //请求失败的回调函数
        console.log(err);
      });
  },
  data: function() {
    return {
      tip: false,
      time: "",
      obj: {
        output: "",
        isEnd: false,
        speed: 300,
        singleBack: false,
        sleep: 0,
        type: "rollback",
        backSpeed: 40,
        sentencePause: true
      },
      articleList: [],
      newestArticleList: [],
      hottestArticleList: [],
      current: 1
    };
  },
  methods: {
    runTime() {
      var timeold =
        new Date().getTime() -
        new Date(this.blogInfo.websiteConfig.websiteCreateTime).getTime();
      var msPerDay = 24 * 60 * 60 * 1000;
      var daysold = Math.floor(timeold / msPerDay);
      var str = "";
      var day = new Date();
      str += daysold + "天";
      str += day.getHours() + "时";
      str += day.getMinutes() + "分";
      str += day.getSeconds() + "秒";
      this.time = str;
    },
    infiniteHandler($state) {
      let md = require("markdown-it")();
      this.axios
        .get("/api/articles", {
          params: {
            current: this.current
          }
        })
        .then(({ data }) => {
          if (data.data.length) {
            // 去除markdown标签
            data.data.forEach(item => {
              item.articleContent = md
                .render(item.articleContent)
                .replace(/<\/?[^>]*>/g, "")
                .replace(/[|]*\n/, "")
                .replace(/&npsp;/gi, "");
            });
            this.articleList.push(...data.data);
            this.current++;
            $state.loaded();
          } else {
            $state.complete();
          }
        });
    }
  },
  computed: {
    isRight() {
      return function(index) {
        if (index % 2 == 0) {
          return "article-cover left-radius";
        }
        return "article-cover right-radius";
      };
    },
    blogInfo() {
      return this.$store.state.blogInfo;
    },
    isShowSocial() {
      return function(social) {
        return this.blogInfo.websiteConfig.socialUrlList.indexOf(social) != -1;
      };
    },
    cover() {
      var cover = "";
      this.$store.state.blogInfo.pageList.forEach(item => {
        if (item.pageLabel == "home") {
          cover = item.pageCover;
        }
      });
      return "background: url(" + cover + ") center center / cover no-repeat";
    }
  }
};
</script>

<style lang="stylus">
.typed-cursor
  opacity: 1
  animation: blink 0.7s infinite
@keyframes blink
  0%
    opacity: 1
  50%
    opacity: 0
  100%
    opacity: 1
</style>

<style scoped>
.blog-contact a {
  color: #fff !important;
}
.card-info-social {
  line-height: 40px;
  text-align: center;
  margin: 6px 0 -6px;
}
.card-info-social a {
  font-size: 1.5rem;
}
.left-radius {
  border-radius: 2px 2px 2px 2px !important;
  order: 0;
}
.right-radius {
  border-radius: 2px 2px 2px 2px !important;
  order: 1;
}
.article-wrapper {
  font-size: 14px;
}

.hottest a:hover {
  color: #409dfe;
}
@media (min-width: 760px) {
  .blog-title {
    font-size: 2.5rem;
  }
  .blog-intro {
    font-size: 1.5rem;
  }
  .blog-contact {
    display: none;
  }
  .home-container {
    max-width: 1200px;
    margin: 0 auto 28px auto;
    padding: 0 5px;
  }
  .article-card {
    display: flex;
    align-items: center;
    height: 260px;
    width: 100%;
    margin-top: 20px;
  }
  .article-cover {
    overflow: hidden;
    height: 100%;
    width: 45%;
  }
  .on-hover {
    transition: all 0.6s;
  }
  .article-card:hover .on-hover {
    transform: scale(1.1);
  }
  .article-wrapper {
    padding: 0 2.5rem;
    width: 55%;
  }
  .article-wrapper a {
    font-size: 1.5rem;
    transition: all 0.3s;
  }
}
@media (max-width: 759px) {
  .blog-title {
    font-size: 26px;
  }
  .blog-contact {
    font-size: 1.25rem;
    line-height: 2;
  }
  .home-container {
    width: 100%;
    margin: 0 auto 0 auto;
  }
  .article-card {
    margin-top: 1rem;
  }
  .article-cover {
    border-radius: 3px 3px 0 0 !important;
    height: 230px !important;
    width: 100%;
  }
  .article-cover div {
    border-radius: 3px 3px 0 0 !important;
  }
  .article-wrapper {
    padding: 1.25rem 1.25rem 1.875rem;
  }
  .article-wrapper a {
    font-size: 1.25rem;
    transition: all 0.3s;
  }
}
.scroll-down i {
  font-size: 2rem;
}
.article-wrapper a:hover {
  color: #c7c7d9;
}
.article-info {
  font-size: 95%;
  color: #858585;
  line-height: 2;
  margin: 0.375rem 0;
}
.article-info a {
  font-size: 95%;
  color: #858585 !important;
}
.article-content {
  line-height: 2;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.blog-wrapper {
  position: sticky;
  top: 10px;
}
.blog-card {
  line-height: 2;
  padding: 1.25rem 1.5rem;
}
.author-wrapper {
  text-align: center;
}
.author-avatar {
  width: 100px;
  height: 100px;
  border: 5px solid #e8e8e8;
  border-radius: 50%;
}
.web-info {
  padding: 0.25rem;
  font-size: 0.875rem;
}
.big i {
  color: #59c763;
  animation: big 0.8s linear infinite;
}
hr {
  border: 2px dotted #e3d2fd;
  margin: 10px 0;
}
@keyframes big {
  0%,
  100% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.2);
  }
}
</style>
