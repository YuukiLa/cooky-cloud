## 生成jks
- 生成
```
keytool -genkey 
        -alias cooky 
        -keypass cooky8 
        -keyalg RSA 
        -keysize 1024 
        -keystore cooky.jks  
        -storepass cooky8
```

- 查看公钥保存在txt中 
```
keytool -list -rfc --keystore cooky.jks | openssl x509 -inform pem -pubkey
```