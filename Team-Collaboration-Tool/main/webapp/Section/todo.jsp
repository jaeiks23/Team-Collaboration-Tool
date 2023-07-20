<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
 <link href="../css/styles.css" rel="stylesheet" />
</head>
<body>
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="index.jsp">Hello Teams</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Search for..."
					aria-label="Search for..." aria-describedby="btnNavbarSearch" />
				<button class="btn btn-primary" id="btnNavbarSearch" type="button">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!">Settings</a></li>
					<li><a class="dropdown-item" href="#!">Activity Log</a></li>
					<li><hr class="dropdown-divider" /></li>
					<li><a class="dropdown-item" href="#!">Logout</a></li>
				</ul></li>
		</ul>
	</nav>
	<div class="card-body">
		<h4 class="card-title">To do list</h4>
		<div class="add-items d-flex">
			<input type="text" class="form-control todo-list-input"
				placeholder="enter task..">
			<button class="add btn btn-primary todo-list-add-btn">Add</button>
		</div>
		<div class="list-wrapper">
			<ul
				class="d-flex flex-column-reverse text-white todo-list todo-list-custom">
				<li>
					<div class="form-check form-check-primary">
						<label class="form-check-label"> <input class="checkbox"
							type="checkbox"> Create invoice
						</label>
					</div> <i class="remove mdi mdi-close-box"></i>
				</li>
				<li>
					<div class="form-check form-check-primary">
						<label class="form-check-label"> <input class="checkbox"
							type="checkbox"> Meeting with Alita
						</label>
					</div> <i class="remove mdi mdi-close-box"></i>
				</li>
				<li class="completed">
					<div class="form-check form-check-primary">
						<label class="form-check-label"> <input class="checkbox"
							type="checkbox" checked> Prepare for presentation
						</label>
					</div> <i class="remove mdi mdi-close-box"></i>
				</li>
				<li>
					<div class="form-check form-check-primary">
						<label class="form-check-label"> <input class="checkbox"
							type="checkbox"> Plan weekend outing
						</label>
					</div> <i class="remove mdi mdi-close-box"></i>
				</li>
				<li>
					<div class="form-check form-check-primary">
						<label class="form-check-label"> <input class="checkbox"
							type="checkbox"> Pick up kids from school
						</label>
					</div> <i class="remove mdi mdi-close-box"></i>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>