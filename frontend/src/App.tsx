import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router';
import Layout from "./ui/components/Layout/Layout";
import Index from "./ui/pages/Index";
import AccommodationsPage from "./ui/pages/AccommodationsPage";
import AccommodationDetailPage from "./ui/pages/AccommodationDetailPage";
import HostsPage from "./ui/pages/HostsPage";
import HostDetailPage from "./ui/pages/HostDetailPage";
import CountriesPage from "./ui/pages/CountriesPage";
import CountryDetailPage from "./ui/pages/CountryDetailPage";
import LoginPage from "./ui/pages/LoginPage";
import RegisterPage from "./ui/pages/RegisterPage";
import PrivateRoute from "./ui/components/PrivateRouter";


function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Layout />}>
                  <Route index element={<Index />} />
                  <Route path="accommodations" element={<PrivateRoute><AccommodationsPage /></PrivateRoute>} />
                  <Route path="accommodations/:id" element={<PrivateRoute><AccommodationDetailPage /></PrivateRoute>} />
                  <Route path="hosts" element={<PrivateRoute><HostsPage /></PrivateRoute>} />
                  <Route path="hosts/:id" element={<PrivateRoute><HostDetailPage /></PrivateRoute>} />
                  <Route path="countries" element={<PrivateRoute><CountriesPage /></PrivateRoute>} />
                  <Route path="countries/:id" element={<PrivateRoute><CountryDetailPage /></PrivateRoute>} />
              </Route>
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
          </Routes>
      </BrowserRouter>
  );
}

export default App;
