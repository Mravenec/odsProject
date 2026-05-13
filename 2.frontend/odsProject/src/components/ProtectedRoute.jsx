import React from 'react';
import { Navigate } from 'react-router-dom';
import { usePermissions } from '../hooks/usePermissions';

export default function ProtectedRoute({ require, redirectTo = '/dashboard', children }) {
  const perms = usePermissions();
  if (!require) return children;
  if (!perms[require]) return <Navigate to={redirectTo} replace />;
  return children;
}
