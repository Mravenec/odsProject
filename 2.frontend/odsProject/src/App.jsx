import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, useParams } from 'react-router-dom';
import { AuthProvider, useAuth } from './hooks/useAuth.jsx';
import LoginPage from './pages/LoginPage/LoginPage.jsx';
import DashboardPage from './pages/DashboardPage/DashboardPage.jsx';
import ProjectCreationPage from './pages/ProjectCreationPage/ProjectCreationPage.jsx';
import ProjectResultsPage from './pages/ProjectResultsPage/ProjectResultsPage.jsx';
import ProjectListPage from './pages/ProjectListPage/ProjectListPage.jsx';
import AdminProjectOverviewPage from './pages/Admin/Overview/OverviewPage.jsx';
import AdminResultsReviewPage from './pages/Admin/Results/ResultsPage.jsx';
import EvaluationPage from './pages/EvaluationPage/EvaluationPage';
import EvaluationQueuePage from './pages/EvaluationQueuePage/EvaluationQueuePage.jsx';
import ForbiddenPage from './pages/ForbiddenPage/ForbiddenPage.jsx';
import ProtectedRoute from './components/ProtectedRoute.jsx';

function App() {
  return (
    <AuthProvider>
      <Router><AppContent /></Router>
    </AuthProvider>
  );
}

function AuditProjectRedirect() {
  const { projectId } = useParams();
  return <Navigate to={`/evaluacion/${projectId}`} replace />;
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
          <span className="loader-subtext">Verificando {hasToken ? 'sesión' : 'acceso'}</span>
        </div>
      </div>
    );
  }
  if (!isAuthenticated || !user || !user.id) return <LoginPage />;

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/projects" element={<ProjectListPage />} />
        <Route path="/projects/:projectId/results" element={<ProjectResultsPage />} />
        <Route path="/forbidden" element={<ForbiddenPage />} />

        {/* Sprint 10: gestor crea (admin/auditor/consultor no) */}
        <Route path="/projects/create" element={
          <ProtectedRoute require="canCreateProject" redirectTo="/forbidden">
            <ProjectCreationPage />
          </ProtectedRoute>
        }/>

        {/* Sprint 3: cola de evaluación (admin/evaluador) */}
        <Route path="/evaluacion" element={
          <ProtectedRoute require="canViewAuditQueue" redirectTo="/forbidden">
            <EvaluationQueuePage />
          </ProtectedRoute>
        }/>
        <Route path="/audit" element={<Navigate to="/evaluacion" replace />} />

        {/* Sprint 3: workbench de evaluación */}
        <Route path="/evaluacion/:projectId" element={
          <ProtectedRoute require="canEnterMeasurements" redirectTo="/forbidden">
            <EvaluationPage />
          </ProtectedRoute>
        }/>
        <Route path="/audit/:projectId" element={<AuditProjectRedirect />} />

        {/* Legacy: /projects/:id/evaluation también gated igual */}
        <Route path="/projects/:projectId/evaluation" element={
          <ProtectedRoute require="canEnterMeasurements" redirectTo="/forbidden">
            <EvaluationPage />
          </ProtectedRoute>
        }/>

        {/* Admin */}
        <Route path="/admin/projects" element={
          <ProtectedRoute require="canViewAdminPanel" redirectTo="/forbidden">
            <AdminProjectOverviewPage />
          </ProtectedRoute>
        }/>
        <Route path="/admin/results/:projectId" element={
          <ProtectedRoute require="canViewAdminPanel" redirectTo="/forbidden">
            <AdminResultsReviewPage />
          </ProtectedRoute>
        }/>

        <Route path="*" element={<Navigate to="/dashboard" replace />} />
      </Routes>
    </div>
  );
}

export default App;
