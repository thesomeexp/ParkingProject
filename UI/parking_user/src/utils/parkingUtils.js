import * as projectConf from '../constants/projectConf'
export function getAcceptLanguage() {
    let lan = window.localStorage.getItem(projectConf.language_key)
    if (lan === null) {
        window.localStorage.setItem(projectConf.language_key,
            projectConf.language_default_value)
        return projectConf.language_default_value
    } else {
        return lan
    }
}
export function setAuthorization(authorization) {
    return window.localStorage.setItem(projectConf.jwt_key, authorization)
}
export function getAuthorization() {
    return projectConf.jwt_prefix + window.localStorage.getItem(projectConf.jwt_key)
}
export function checkLogin() {
    let jwt = window.localStorage.getItem(projectConf.jwt_key)
    if (jwt !== null && jwt !== 'null') {
        // 判断是否过期
        let jwt1 = decodeURIComponent(escape(window.atob(jwt.split('.')[1])))
        let exp = JSON.parse(jwt1).exp
        // 时间戳位数
        let nowT = new Date().getTime() / 1000
        if (nowT > exp) {
            console.log('jwt过期')
            return false
        }
        return true
    } else {
        console.log('jwt为null')
        return false
    }
}
export function goBack(that) {
    window.history.length > 1 ? that.$router.go(-1) : that.$router.push('/')
}
export function success(from, msg) {
    from.$message({
        message: msg,
        type: 'success'
    });
}
export function error(from, msg) {
    from.$message({
        message: msg,
        type: 'error'
    });
}
export function warning(from, msg) {
    from.$message({
        message: msg,
        type: 'warning'
    });
}