
# Spring API Projesi

Bu proje, **Spring Framework** kullanarak geliştirilmiş bir RESTful API uygulamasıdır. Uygulama, kullanıcı işlemleri ve veri yönetimi gibi temel işlevsellikler sunar. Bu README dosyası, projenin nasıl kurulup çalıştırılacağına dair adımları içermektedir.

## Teknolojiler

- **Spring Boot**: Uygulama geliştirme için kullanıldı.
- **Spring Data JPA**: Veritabanı işlemleri için kullanıldı.
- **Oracle (21c yada 19c Database)**: Veri depolama için oracle veritabanı kullanıldı.
- **JWT (JSON Web Token)**: Kimlik doğrulama için kullanıldı.
- **Maven**: Proje yönetimi ve bağımlılık yönetimi için kullanıldı.
- **Lombok**: Java kodunun daha sade olmasını sağlamak için kullanıldı.

---

## Başlangıç

Projeyi çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

### Gereksinimler

- **JDK 11 veya daha yeni bir sürüm**
- **Maven** (bağımlılıkları yönetmek için)

---

### Projeyi Klonlamak

İlk olarak projeyi GitHub'dan klonlayın:

```bash
git clone [https://github.com/your-username/your-repository-name.git](https://github.com/RHK1335/Spring-API.git)
cd Spring-API
```

---

### Bağımlılıkları Yüklemek

Projede gerekli bağımlılıkları yüklemek için aşağıdaki komutu çalıştırın:

```bash
mvn clean install
```

Bu komut, Maven'ı kullanarak gerekli bağımlılıkları projeye dahil edecektir.

---

### Uygulamayı Çalıştırmak

Spring Boot uygulamasını aşağıdaki komutla çalıştırabilirsiniz:

```bash
mvn spring-boot:run
```

Uygulama başarılı bir şekilde başlatıldığında, varsayılan olarak `http://localhost:8080` adresinde çalışmaya başlayacaktır.

---
