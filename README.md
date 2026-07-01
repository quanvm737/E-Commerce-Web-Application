🛒 E-Commerce Web Application
A full-stack Java Web application for managing an e-commerce platform — catalog, shopping cart, and order tracking in one place.

✨ Features
📅 Account management (Login, Signup, Account Activation)
🛍️ Storefront catalog with dynamic product search
🛒 Interactive shopping cart operations
💳 Secure checkout and order transaction logging
📊 Admin dashboard for managing products, categories, accounts, and orders

🛠️ Tech Stack
| Layer | Technology |
| :--- | :--- |
| Frontend | JSP, JSTL, HTML, CSS, JavaScript, Bootstrap |
| Backend | Java Servlet, JPA (EclipseLink), WebFilters |
| Database | Microsoft SQL Server (JDBC) |
| Server | Apache Tomcat |

🚀 Getting Started
Prerequisites
- NetBeans IDE / Eclipse / IntelliJ IDEA
- Apache Tomcat Server (v10 or later compatible)
- Microsoft SQL Server

Installation
1. Clone the repo
   ```bash
   git clone https://github.com/quanvm737/E-Commerce-Web-Application.git
   cd E-Commerce-Web-Application
   ```
2. Database Configuration
   - Create a database named `SE1925_VuongMinhQuan_SE204737_workshop2` in Microsoft SQL Server.
   - Configure your SQL Server database credentials (username and password) in `src/conf/persistence.xml`.

3. Run the Application
   - Open the project in NetBeans IDE.
   - Clean and Build the project.
   - Deploy and Run on Apache Tomcat server.

📁 Project Structure
```text
E-Commerce-Web-Application/
├── src/java/
│   ├── Controller/      # Servlets handling routing & business logic
│   ├── filter/          # Authorization filters
│   └── model/           # JPA Entities & DAO classes
├── web/                 # JSP views, assets & web configuration
├── build.xml            # Ant build script
└── README.md
```

👤 Author
**Vuong Minh Quan**
- GitHub: [@quanvm737](https://github.com/quanvm737)
