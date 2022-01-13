import { 
  BrowserRouter, 
  Route, 
  Navigate, 
  Routes, 
  /* Outlet, 
  useLocation  */
} from 'react-router-dom';

import Auth from './pages/Auth/Auth';
import Home from './pages/Home'
import DashboardAdmin from './pages/Admin/DashboardAdmin';
import DashboardDoctor from './pages/Doctor/DashboardDoctor';
import PatientHome from './pages/Patient/PatientHome';

import './App.css'

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/auth" element={<Auth />} />
        {/* Rutas Privadas */}
        <Route path="/" element={<AuthUser />} />

        {/* <Route element={<DoctorAuth />}>
              <Route path="" element={} />
              
            </Route>

            <Route element={<AdminAuth />}>
              
            </Route> */}

        {/* <Route path="*" element={<NotFound />} /> */}
      </Routes>

    </BrowserRouter>
  )
}

function AuthUser() {
  const user = localStorage.getItem('user')

  if (!user) {
    return <Home />;
  } 

  if (user === 'user') {
    return <PatientHome />;
  }

  if (user === 'doctor') {
    return <DashboardDoctor />;
  }

  if (user === 'admin') {
    return <DashboardAdmin />;
  }

  return <Navigate to="/auth" />;

}

export default App