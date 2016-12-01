package com.example.administrator.mylvshi.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class HotUser {

    /**
     * self : http://api.lvshiv.com/lvshiv/travelVideos/hot
     */

    private LinksBean links;
    /**
     * id : 4239
     * createdDate : 1480098187111
     * poi : {"poiName":"琉璃路","province":"四川省","city":"成都市","region":"","address":"四川省成都市锦江区","lat":30.607064,"lng":104.094961,"isChina":false}
     * intro : 王小波说：“一辈子很长、要和有趣的人在一起”
     有时 并不是你不有趣，而是还没有遇到那个 和你的能量场能势均力敌的对手！

     Enjoy ：Travel、Dream、Love、and You.
     * videoDuration : 110
     * viewedCount : 102
     * commentCount : 1
     * favorCount : 2
     * user : {"id":44602,"nickname":"流浪在成都","followed":false,"role":0,"imageHref":"http://cdn.lvshiv.com/FoET8rvaXmrtThJDrtKPFBuib_HE"}
     * type : 1
     * ad : true
     * collected : false
     * favored : false
     * duration : 110
     * videoHref : http://cdn.lvshiv.com/Dre5_3L73RGX-6tE7Yw0J-LyAOI=/lrxBt4qAjxNvW7joscEx_DhI74TJ
     * imageHref : http://cdn.lvshiv.com/nUyP59nq1hJe-8iSZV9huoGHk8Y=/lrxBt4qAjxNvW7joscEx_DhI74TJ
     * summary : 王小波说：“一辈子很长、要和有趣的人在一起”有时并不是你不有趣，而是还没有遇到那个和你的能量场...
     * links : {"self":"http://api.lvshiv.com/lvshiv/travelVideos/4239","share":"http://api.lvshiv.com/lvshiv/travelVideos/4239/share"}
     */

    private List<ContentBean> content;

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class LinksBean {
        private String self;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }
    }

    public static class ContentBean {
        private int id;
        private long createdDate;
        /**
         * poiName : 琉璃路
         * province : 四川省
         * city : 成都市
         * region :
         * address : 四川省成都市锦江区
         * lat : 30.607064
         * lng : 104.094961
         * isChina : false
         */

        private PoiBean poi;
        private String intro;
        private int videoDuration;
        private int viewedCount;
        private int commentCount;
        private int favorCount;
        /**
         * id : 44602
         * nickname : 流浪在成都
         * followed : false
         * role : 0
         * imageHref : http://cdn.lvshiv.com/FoET8rvaXmrtThJDrtKPFBuib_HE
         */

        private UserBean user;
        private int type;
        private boolean ad;
        private boolean collected;
        private boolean favored;
        private int duration;
        private String videoHref;
        private String imageHref;
        private String summary;
        /**
         * self : http://api.lvshiv.com/lvshiv/travelVideos/4239
         * share : http://api.lvshiv.com/lvshiv/travelVideos/4239/share
         */

        private LinksBean links;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public PoiBean getPoi() {
            return poi;
        }

        public void setPoi(PoiBean poi) {
            this.poi = poi;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(int videoDuration) {
            this.videoDuration = videoDuration;
        }

        public int getViewedCount() {
            return viewedCount;
        }

        public void setViewedCount(int viewedCount) {
            this.viewedCount = viewedCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getFavorCount() {
            return favorCount;
        }

        public void setFavorCount(int favorCount) {
            this.favorCount = favorCount;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isAd() {
            return ad;
        }

        public void setAd(boolean ad) {
            this.ad = ad;
        }

        public boolean isCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public boolean isFavored() {
            return favored;
        }

        public void setFavored(boolean favored) {
            this.favored = favored;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getVideoHref() {
            return videoHref;
        }

        public void setVideoHref(String videoHref) {
            this.videoHref = videoHref;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public static class PoiBean {
            private String poiName;
            private String province;
            private String city;
            private String region;
            private String address;
            private double lat;
            private double lng;
            private boolean isChina;

            public String getPoiName() {
                return poiName;
            }

            public void setPoiName(String poiName) {
                this.poiName = poiName;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public boolean isIsChina() {
                return isChina;
            }

            public void setIsChina(boolean isChina) {
                this.isChina = isChina;
            }
        }

        public static class UserBean {
            private int id;
            private String nickname;
            private boolean followed;
            private int role;
            private String imageHref;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getImageHref() {
                return imageHref;
            }

            public void setImageHref(String imageHref) {
                this.imageHref = imageHref;
            }
        }

        public static class LinksBean {
            private String self;
            private String share;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }

            public String getShare() {
                return share;
            }

            public void setShare(String share) {
                this.share = share;
            }
        }
    }
}
