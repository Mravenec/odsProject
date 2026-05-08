import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './hooks/useAuth.jsx';
import LoginPage from './pages/LoginPage/LoginPage.jsx';
import DashboardPage from './pages/DashboardPage/DashboardPage.jsx';
import ProjectCreationPage from './pages/ProjectCreationPage/ProjectCreationPage.jsx';
import ProjectResultsPage from './pages/ProjectResultsPage/ProjectResultsPage.jsx';
import ProjectListPage from './pages/ProjectListPage/ProjectListPage.jsx';
import AdminProjectOverviewPage from './pages/Admin/Overview/OverviewPage.jsx';
import AdminResultsReviewPage from './pages/Admin/Results/ResultsPage.jsx';
import EvaluationPage from './pages/EvaluationPage/EvaluationPage';

function App() {
  return (
    <AuthProvider>
      <Router>
        <AppContent />
      </Router>
    </AuthProvider>
  );
}

function AppContent() {
  const { isAuthenticated, user, loading } = useAuth();
  const hasToken = !!localStorage.getItem('token');
  
  if (loading) {
    return (
      <div className="global-loader-container">
        <div className="loader"></div>
        <div className="loader-content">
          <p>Sincronizando con ODS Core...</p>
          <span className="loader-subtext">Verificando credenciales de {hasToken ? 'sesión' : 'acceso'}</span>
        </div>
      </div>
    );
  }

  if (!isAuthenticated || !user || !user.id) {
    return <LoginPage />;
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/projects" element={<ProjectListPage />} />
        <Route path="/projects/create" element={<ProjectCreationPage />} />
        <Route path="/projects/:projectId/results" element={<ProjectResultsPage />} />
        <Route path="/projects/:projectId/evaluation" element={<EvaluationPage />} />
        
        {user?.role === 'admin' && (
          <>
            <Route path="/admin/projects" element={<AdminProjectOverviewPage />} />
            <Route path="/admin/results/:projectId" element={<AdminResultsReviewPage />} />
          </>
        )}
        
        <Route path="*" element={<Navigate to="/dashboard" replace />} />
      </Routes>
    </div>
  );
}

export default App;
