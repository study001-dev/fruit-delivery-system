const base = {
    get() {
        return {
            url : "http://127.0.0.1:8080/springboot/",
            name: "springboot",
            // 退出到首页链接
            indexUrl: 'http://127.0.0.1:8080/springboot/front/h5/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "O2O水果食品订购"
        } 
    }
}
export default base
