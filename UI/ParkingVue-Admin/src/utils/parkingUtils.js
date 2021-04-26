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
